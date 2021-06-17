Feature: Création d'une nouvelle partie de tennis
    Scenario: Création avec deux fois le même joueur
        Given Nouvelle partie de tennis
        When L'utilisateur entre deux fois le même joueur
        Then Il recoit un message d'erreur

    Scenario: Les joueurs ne peuvent etre nulls
        Given Nouvelle partie de tennis
        When L'utilisateur entre au moins un joueur null
        Then Il recoit un message d'erreur.

    Scenario: Création avec deux joueurs
        Given Nouvelle partie de tennis
        When L'utilisateur entre deux joueurs
        Then La partie contient les deux joueurs

    Scenario: Les Scores sont de 0 a la création
        Given Nouvelle partie de tennis
        When L'utilisateur entre deux joueurs
        Then Les scores sont initialement a 0;
    