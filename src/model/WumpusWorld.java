package model;

/**
 * Object used to generate a world for the wumpus problem, with a few fixed and
 * a few variable parameters
 *
 * @author Paul Givel and Guillaume Hartenstein
 */
public class WumpusWorld {

//    private int wumpusQty = 1;
//    private int pitQty = 1;
//    private int goldQty = 1;
    private int height = 2;
    private int width = 3;
    private final UniqueList<Integer> trueFalseList = new UniqueList<>();
    private final World wumpusWorld = new World();
    private final SysObject agent = new SysObject("agent", wumpusWorld);
    private final SysObject wumpus = new SysObject("wumpus", wumpusWorld);
    private final SysObject pit = new SysObject("pit", wumpusWorld);
    private final SysObject gold = new SysObject("gold", wumpusWorld);

    public WumpusWorld() {
        trueFalseList.add(0);
        trueFalseList.add(1);
    }

    public void setDimensions(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public World generateWorld() {
        generateAgent();
        generateWumpus();
        generatePit();
        generateGold();
        generateActions();

        return wumpusWorld;
    }

    private void generateAgent() {
        ObjectProperty propertyHasGold = new ObjectProperty(trueFalseList, "hasGold");
        agent.addProperty(propertyHasGold);

        agent.addProperty(generateXPositionProperty());
        agent.addProperty(generateYPositionProperty());

        ObjectProperty propertyIsAlive = new ObjectProperty(trueFalseList, "isAlive");
        agent.addProperty(propertyIsAlive);

        ObjectProperty propertyHasShot = new ObjectProperty(trueFalseList, "hasShot");
        agent.addProperty(propertyHasShot);

        wumpusWorld.addObject(agent);
    }

    private void generateWumpus() {
        wumpus.addProperty(generateXPositionProperty());
        wumpus.addProperty(generateYPositionProperty());

        ObjectProperty propertyIsAlive = new ObjectProperty(trueFalseList, "isAlive");
        wumpus.addProperty(propertyIsAlive);

        wumpusWorld.addObject(wumpus);
    }

    private void generatePit() {
        pit.addProperty(generateXPositionProperty());
        pit.addProperty(generateYPositionProperty());

        wumpusWorld.addObject(pit);
    }

    private void generateGold() {
        gold.addProperty(generateXPositionProperty());
        gold.addProperty(generateYPositionProperty());

        ObjectProperty propertyIsPickedUp = new ObjectProperty(trueFalseList, "isPickedUp");
        gold.addProperty(propertyIsPickedUp);

        wumpusWorld.addObject(gold);
    }

    private ObjectProperty generateXPositionProperty() {
        ObjectProperty xPositionProperty = new ObjectProperty("xPosition");
        for (int i = 0; i < width; i++) {
            xPositionProperty.addPossibleValue(i);
        }
        return xPositionProperty;
    }

    private ObjectProperty generateYPositionProperty() {
        ObjectProperty yPositionProperty = new ObjectProperty("yPosition");
        for (int i = 0; i < height; i++) {
            yPositionProperty.addPossibleValue(i);
        }
        return yPositionProperty;
    }

    private void generateActions() {
        addMoveAgent();
        addMoveWumpus();
        addShootWumpus(); //TODO: make it so that it is adjacent instead of on the same case.
        addFallAgent();
        addFallWumpus();
        addEatAgent();
        addPickUpGold();
    }

    private void addMoveAgent() {
        Action moveAgent = new Action("moveAgent");
        moveAgent.addPreCondition(new PropertyValue(agent, "isAlive", 1));
        wumpusWorld.addPossibleAction(moveAgent);
    }

    private void addFallAgent() {
        Action fallAgent = new Action("fallAgent");
        fallAgent.addPreCondition(new PropertyValue(agent, "isAlive", 1));
        fallAgent.addPreCondition(new Equality(agent, "xPosition", pit, "xPosition"));
        fallAgent.addPreCondition(new Equality(agent, "yPosition", pit, "yPosition"));
        fallAgent.addPostCondition(new PropertyValue(agent, "isAlive", 0));
        wumpusWorld.addPossibleAction(fallAgent);
    }

    private void addFallWumpus() {
        Action fallWumpus = new Action("fallWumpus");
        fallWumpus.addPreCondition(new PropertyValue(wumpus, "isAlive", 1));
        fallWumpus.addPreCondition(new Equality(wumpus, "xPosition", pit, "xPosition"));
        fallWumpus.addPreCondition(new Equality(wumpus, "yPosition", pit, "yPosition"));
        fallWumpus.addPostCondition(new PropertyValue(wumpus, "isAlive", 0));
        wumpusWorld.addPossibleAction(fallWumpus);
    }

    private void addMoveWumpus() {
        Action moveWumpus = new Action("moveWumpus");
        moveWumpus.addPreCondition(new PropertyValue(wumpus, "isAlive", 1));
        wumpusWorld.addPossibleAction(moveWumpus);
    }

    private void addShootWumpus() {
        Action shootWumpus = new Action("shootWumpus");
        shootWumpus.addPreCondition(new PropertyValue(agent, "isAlive", 1));
        shootWumpus.addPreCondition(new PropertyValue(agent, "hasShot", 0));
        shootWumpus.addPreCondition(new Equality(agent, "xPosition", wumpus, "xPosition"));
        shootWumpus.addPreCondition(new Equality(agent, "yPosition", wumpus, "yPosition"));
        shootWumpus.addPostCondition(new PropertyValue(wumpus, "isAlive", 0));
        shootWumpus.addPostCondition(new PropertyValue(agent, "hasShot", 1));
        wumpusWorld.addPossibleAction(shootWumpus);
    }

    private void addEatAgent() {
        Action eatAgent = new Action("eatAgent");
        eatAgent.addPreCondition(new PropertyValue(agent, "isAlive", 1));
        eatAgent.addPostCondition(new PropertyValue(wumpus, "isAlive", 1));
        eatAgent.addPreCondition(new Equality(agent, "xPosition", wumpus, "xPosition"));
        eatAgent.addPreCondition(new Equality(agent, "yPosition", wumpus, "yPosition"));
        eatAgent.addPostCondition(new PropertyValue(agent, "isAlive", 0));
        wumpusWorld.addPossibleAction(eatAgent);
    }

    private void addPickUpGold() {
        Action pickUpGold = new Action("pickUpGold");
        pickUpGold.addPreCondition(new PropertyValue(agent, "isAlive", 1));
        pickUpGold.addPreCondition(new PropertyValue(agent, "hasGold", 0));
        pickUpGold.addPreCondition(new PropertyValue(gold, "isPickedUp", 0));
        pickUpGold.addPreCondition(new Equality(agent, "xPosition", gold, "xPosition"));
        pickUpGold.addPreCondition(new Equality(agent, "yPosition", gold, "yPosition"));
        pickUpGold.addPreCondition(new PropertyValue(agent, "hasGold", 1));
        pickUpGold.addPreCondition(new PropertyValue(gold, "isPickedUp", 1));
        wumpusWorld.addPossibleAction(pickUpGold);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public SysObject getAgent() {
        return agent;
    }

    public SysObject getWumpus() {
        return wumpus;
    }

    public SysObject getGold() {
        return gold;
    }

    public SysObject getPit() {
        return pit;
    }
}
