# Tennis Kata avec Java et JUnit

## Présentation
Le but de cette exercice est de faire une application pour compter les points aux tennis en utilisant la methode TDD. Nous utiliserons le langage Java et la librairie de test JUnit.

## Définition
* __Test Driven Design [TDD]__ : Methode de développement qui permet de limiter fortement le nombre de bug et d'avoir un developpement Incremental. Le principe : 
    * `_[RED]_` faire un test d'une unité de besoin puis le lancer pour voir qu'il ne valide pas encore notre code.
    * `_[GREEN]_` coder le code minimal qui permet de repondre au test tout en validant les testes précedents.
    * `_[REFECTOR]_` chercher des amelioration pour la syntaxe et la lisibilité du code. Relancer les testes pour verifier qu'il n'y a pas eu de regression avant de passé au test suivent.

    ![RED GREED REFACTOR](./assets/RED-GREEN-REFACTOR.svg)
    
    * La TDD est souvent confondu avec la methode Test First qui a pour principe de commencé par les tests: `Ce n'est pas la même chose!`. En faisant les tests au fur et a mesure du developpement l'on construit notre code en fonction d'un test. Test first ne dirige pas vraiment le developpement. De plus, Test first demande de désactiver des tests et/ou de developper plusieur unité en même temps ce qui ne donne pas forcement les meilleur resultat.
    * TDD permet une meilleure gestion des modifications. Le temps de développement semble plus long en debut de projet car les developpeur doivent faire les tests en même temps que le code. Mais sur le moyen terme, TDD permet de limiter grandement les phases de deboggage qui prennent en général beaucoup de temps.

* __Unité de besoin__ : Le besoin du client ou du PO est une notion fonctionnelle et non technique. Elle peut etre souvent decoupé en plus petite partie que j'appelerai unité de besoin. C'est un besoin unitaire/minimal. C'est a partir de ces besoins unitaire que nous allons décrire nos tests.

* __Hexagonal, Port and Adapters, Clean architecture__ : Pour pouvoir correctement effectuer nos tests, nous devons utiliser une architecture de code la plus independante possible. Pour cela nous devons favoriser au maximum l'injection de dépendence et limité au maximum les couplages dans notre code. Cette architecture permet aussi de ne pas dépendre d'un framework ou d'une librairie : notre code pourra etre utiliser dans n'importe quel environnement. Par exemple votre code metier pourra facilement etre implementé dans angular ou react apres son développent. Ou même etre mis en place dériere une API Rest. Voici 3 architectures : ![n-Tier/Clean/hexagonal](./assets/Clean_Archi.svg)
    * __n-tier__: C'est l'architecture classique de nos application web. L'interface Utilisateur [UI] est connecté aux controllers qui sont dependant de nos services metiers qui eux même utilise la couche reseaux ou infra. Tout etant lier ensemble, il peut etre assez compliqué de détacher notre couche metier(service) pour les tests ou même pour une migration de technologie : `domage!`.
    * __Clean Architecture et Hexagonal__ : ce sont des architectures qui permettent de détacher notre couche métier du reste de notre code. Le métier devient le centre de notre application. Les couches Controllers, Infra et UI ne sont plus que des `adapters` qui s'adaptent à notre code métier. Avec cette vision d'une application, notre code métier peut être développé seul et correspondu directement aux besoins du client ou PO. Ce sont deux architectures identiques : Elles utilisent au maximum `l'injection de dépendances`! Clean architecture est une vision DDD (Domaine Driven Design) de l'hexagonale. Ce sont des architectures à favoriser pour les tests et donc pour la TDD.
* __Fonctionnel vs Technique__ : Une notion importante en TDD et en DDD et l'utilisation de terme fonctionnel plutôt que techniques. En utilisant uniquement des termes techniques nous excluons les fonctionnels du développement. L'un des buts de la méthode TDD est de pouvoir plus facilement expliquer notre code aux Fonctionnels.

    *  Pour cela nous essayerons au maximum d'utiliser dans nos noms de classes, méthodes, variables et paramètres un lexique fonctionnel. En utilisant des termes fonctionnels nous favorisons aussi une meilleure compréhension entre l'équipe de développement et le client et le PO.
        - Par exemple : 
            * `_IUtilisateurRepository::insert_` n'est compréhensible uniquement par les développeurs alors que 
            * `_ToutLesUtilisateurs::ajouterUnNouvelUtilisateur_` est compréhensible par tout le monde. Cette notion est aussi à prendre en compte pour le nom de nos tests : 
    * Les noms de tests techniques ne permettons pas au PO/Client et autre personnel qui ne connaissent pas forcément le monde du développement l'avancement.
        * Par exemple : 
            * `_La méthode sendingtosmtpserver doit lever une exception du type SMTPConnectionFailException si le serveur n'est pas joignable ` n'est pas vraiment compréhensible.
            * `_Si le serveur de mail n'est pas joignable, la demande d'envoi retourne un signal d'erreur ` est bien plus compréhensible.
    * En suivent ce précepte nous limitons les erreurs d'incompréhension qui peuvent faire perdre beaucoup de temps pendant le développement.
    * Il permet aussi de faciliter la reprise du code par la suite.
* __Code coverage__: Pourcentage du code couvert par les tests. Le but est d'avoir 100%. En suivent correctement la TDD, nous devrions avoir 100%. Car un code qui n'est pas couvert est un code qui n'est pas censé être utilisé et donc inutile.

## L'exercice: Un Compteur de point en Java
Une entreprise ayant besoin d'un système pour compter les points aux tennis nous contact. Elle ne sait pas encore comment ils vont l’utiliser : API REST, Angular, Vue Js, CLI, application lourde....

### Les Entités:
Une partie : Représente la partie de tennis en cours.
Les joueurs : Il possède un nom.

### Les besoins
* Un utilisateur peut créer une nouvelle partie.
* Un utilisateur peut choisir les 2 joueurs qui jouerons la partie.
* Au début de la partie les deux joueurs ont 0 points.
* Au début de la partie les deux joueurs ont 0 jeux gagnés.
* Au début de la partie les deux joueurs ont 0 sets gagnés.

* L'utilisateur peut notifier qu'un Joueur a gagné un point.
* Quand un joueur gagne un point, il passe de 0 à 15. Puis de 15 à 30. Puis de 30 à 40.
* Si les deux joueurs sont a egalité a 40 points, si aucun joueur a un avantage, le joueur qui gagne le point gagne un avantage.
* Si les deux joueurs sont a egalité a 40 points, si le perdant a un avantage, alors il le perd.
* Si les deux joueurs sont a egalité a 40 points, si le gagnant a un avantage, alors il gagne le jeu.

* Quand un jeu est gagné, alors les deux joueurs retournent à 0 point.
* Quand un joueur arrive à gagner 6 jeux et que son adversaire 4 ou moins jeux gagnés, alors le joueur gagne un set.
* Quand les deux joueurs ont 5 points alors faut avoir 2 points d'avance et 7 jeux pour gagner le set.

* Quand les deux joueurs ont 6 jeux gagné, alors on passe en `jeux decisif`.
* Quand il y a jeu décisif les points sont compté par point.
* Si un joueur gagne un point pendant un jeu decisif il passe de 0 à 1, puis 2, ... jusqu'à 7. 
* Il faut avoir deux points d'avance pour gagner le jeu et donc le set.

* Le premier joueur a 2 sets gagnés gagne la partie.
* Quand un joueur a gagné, il n'est plus possible de changer les scores.
* L'utilisateur doit être avertie que la partie est finie.

### Developpement
Nous devons créer la classe `CompteurDeScoreTennis` qui contiendra une bonne patie de notre code.
Nous devons aussi créer nos entités `Partie` et `Joueur`.

## Exécuté votre code

