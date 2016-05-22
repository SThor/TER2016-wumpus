# Contexte

Ce programme a été réalisé lors d'un projet T.E.R. ( Travaux d'Études et de Recherche ) de deux mois à l'Institut de Recherche en Informatique de Toulouse ([IRIT](irit.fr)). Il a été réalisé en binôme par Paul Givel (@SThor) et Guillaume Hartenstein (@wierin) sous la direction de [Florence Dupin de Saint-Cyr - Bannay](https://www.irit.fr/~Florence.Bannay/), à partir de travaux effectué par elle-même et [Jérôme Lang](http://www.lamsade.dauphine.fr/~lang/Jerome-french.html). 

## Sujet de recherche scientifique

Le sujet scientifique est celui de l’extrapolation de croyances. On cherche à étudier un système ( ou monde ) dans lequel peuvent s’effectuer des actions régies par des lois données. Le système part d’un état initial pour arriver à un état final en satisfaisant un ensemble de prédicats ( les observations ) à des instants donnés, créant ainsi un scénario.

Une trajectoire représente une suite d’actions menant de l’état initial à l’état final en satisfaisant toutes les observations. Une fois la trajectoire la plus plausible calculée ( ou bien les trajectoires les plus plausibles ), il est possible de donner la réponse à des questions de causalité sur le système — du type : quelle a été la cause de telle conséquence ?

Il s’agit donc de coder un opérateur d'extrapolation : c'est un opérateur qui permet de déduire les trajectoires les plus plausibles expliquant un scénario. Et de l'appliquer à la recherche de causalité et donc de responsabilités dans un scénario.

Chaque système étudié étant différent, il faut pouvoir définir quels objets seront dans le système ainsi que leur propriétés ou états — par exemple, si l’on étudie une porte, celle-ci pourra être ouverte ou fermée. L’ensemble des états des objets du système forme un état global qui peut être étudié pour déduire de manière probabiliste l’action qui s’est déroulé au moment où le système était dans cet état.

## “Hunt the Wumpus”

Le titre de ce projet, “Qui a tué le wumpus”, vient d’un problème courant en Intelligence Artificielle. Ce problème est à l’origine basé sur l’un des premiers jeu informatiques, créé dans les années 70.

Le joueur, par l’intermédiaire de commandes textuelles, se déplace dans un réseau de cavernes, où se trouve aussi, caché, un monstre : le Wumpus. Il y a de plus des fosses ainsi que des chauves-souris. Si le joueur entre dans une pièce où se trouve le Wumpus ou bien une fosse, le personnage meurt, et le joueur perd la partie. Pour l’aider, lorsqu’il se trouve dans une pièce adjacente à l’un ou l’autre des objets possibles, cela est indiqué : l’odeur du Wumpus, le bruit des chauves-souris, la brise des fosses. Le joueur doit donc par déduction trouver la salle dans laquelle est le Wumpus, et, sans y entrer, lui décocher une flèche.

De nombreuses versions de ce jeu ont par la suite été créées, en réalisant par exemple une interface graphique ou en modifiant légèrement les règles. Aujourd’hui, il existe de nombreuses références à ce jeu dans des jeux, jeu-vidéos et logiciels, mais celle qui nous intéresse est un problème d’Intelligence Artificielle décrit par Stuart Russel et Peter Norvig dans [Artificial Intelligence : A Modern Approach](https://en.wikipedia.org/wiki/Artificial_Intelligence:_A_Modern_Approach).

Dans la version décrite dans ce livre, le terrain sur lequel évolue l’agent est une grille rectangulaire, de taille variable. Une autre modification importante est le remplacement des chauves-souris par un lingot d’or, qui est l’objectif principal de l’agent. Notre version s’éloigne encore légèrement de l’original.

# Utilisation

L'ensemble des sources du projet est disponible sur la page GitHub, et une version exécutable du logiciel sous forme de fichier `*.jar` se trouve dans le dossier `dist` du *repository*.

## Interface générale

À l'ouverture du logiciel, un monde ( très simple ) est déjà présent, ainsi qu'un scénario.
* L'onglet `Objects` permet la gestion des objets du monde. Il est possible d'ajouter des objets, des propriétés à chaque objet, des valeurs possibles à ces propriétés ( sous forme d'entier, à vous ensuite d'interpréter la signification de ces entiers ), et de supprimer l'un de ces éléments.
* L'onglet `Actions` permet la gestion des actions possibles dans ce monde. Chaque action est définie par un nom, un ensemble de préconditions et un ensemble de postconditions. Il est donc possible d'ajouter une action et, après l'avoir sélectionnée dans la liste, en supprimer une. La sélection d'une action affiche ses pré- et post-conditions dans les listes appropriées, et il est là encore possible d'en ajouter et d'en supprimer. Il existe deux types de conditions :
  * Le premier type est vérifié lorsqu'une propriété d'un objet prend une valeur désirée.
  * Le second type est vérifié lorsqu'une propriété d'un premier objet et une propriété d'un second objet prennent la même valeur.
* L'onglet `Scenario` permet quand à lui d'effectuer des observations sur le monde qui vient d'être défini, selon la syntaxe `xml` décrite dans la partie suivante.
Enfin, une fois le monde et les observations définis, il est possible de le "résoudre" par l'intermédiaire du menu `solver`. Une fenêtre permettant de choisir l'algorithme désiré, avec un petit texte explicatif pour chaque algorithme, s'ouvre, et l'on peut résoudre le monde. Cela ouvre une nouvelle fenêtre montrant les différentes trajectoires possibles, depuis laquelle il est possible d'exporter ces trajectoires sous forme d'image.

## Exportations et importations via `xml`

Chaque monde et chaque scénario peut-être importé et exporté via un fichier `xml`, à travers les menus appropriés. De plus, les observations sont réalisées elle aussi sous la forme de balises `xml`. Les syntaxes respectives du monde et des scénarios peuvent êtres simplement observées en créant dans le logiciel un monde et un scénario et en enregistrant puis consultant les fichiers `xml` ainsi créés. Voici toutefois une description complète des balises disponibles.

### Syntaxe du monde

La balise racine doit être appelée `<world>`. Elle doit comporter deux types de balises, `<objects>` et <actions>`.

Chaque balise `<object>` possède un attribut `name` correspondant au nom de cet objet. Une  balise `<object>` comporte de plus un ensemble de balises `<property>`. Chaque balise `<property>` possède un attribut `name` correspondant au nom de cette propriété. Elle comporte aussi un ensemble de balises `<value>`, chacune ayant encore une fois un attribut `name` correspondant à une valeur possible de la propriété.

Une action possède, elle, deux balises, l'une `<preconditions>` et l'autre `<postconditions>` qui sont organisées de la même manière. Elles possèdent un ensemble de conditions qui peuvent être soit une `<propertyValue>`, soit une `<equality>`, comme expliqué dans la partie sur l'interface. Une balise `<propertyValue>` possède trois attributs :
* `object` correspond au nom de l'objet visé
* `property`correspond au nom de la propriété visée
* `wanted_value` correspond à la valeur attendue pour cette propriété
Une balise `<equality>` possède quatre attributs :
* `object1` correspond au nom du premier objet visé
* `property1`correspond au nom de la propriété de l'objet visée
* `object2` correspond au nom du second objet visé
* `property2`correspond au nom de la propriété de l'objet visée

### Syntaxe des observations

Pour les observations, il y a deux cas. Si l'on parle du fichier `xml` contenant l'ensemble du scénario, il faut la balise racine `<scenario>`. Ce fichier comporte à l'intérieur de cette balise un ensemble de balises d'observations, qui chacune correspond à un instant. Dans l'éditeur de scénario interne au logiciel, pour chaque instant c'est une de ces balises qui doit être définie.

Il existe plusieurs types de balises d'observations :
* la balise `<noObservation>`. Elle peut être utile car l'ensemble des instant du scénario n'est constitué que des observations. Pour avoir un instant sans observations, cette balise est donc nécessaire.
* la balise `<propertyValue>` telle que définie pour les conditions des actions
* la balise `<action>` pour observer une action. **Attention, cette fonctionnalité n'est pas complètement implémentée, n'est pas utilisable et provoquera une exception.**
* une balise `<operation>`. Une opération contient une ou plusieurs balise d'observation ( de n'importe quel type ), et permet de les combiner. Il en existe trois types, qui sont définis par l'attribut `type` :
  * `or` correspond à un ou logique entre les différentes balises contenues
  * `and`correspond à un et logique entre ces balises
  * `not` correspond à la négation de la balise contenue. **Attention, pour ce type, il ne faut qu'une seule balise à l'intérieur de l'opération.**

# Références

* [*Artificial Intelligence : A Modern Approach*](https://en.wikipedia.org/wiki/Artificial_Intelligence:_A_Modern_Approach)  par Stuart J. Russell et Peter Norvig
* [Belief extrapolation (or how to reason about observations and unpredicted change)](http://www.sciencedirect.com/science/article/pii/S0004370210001815) par Florence Dupin de Saint-Cyr - Bannay et Jérôme Lang.
