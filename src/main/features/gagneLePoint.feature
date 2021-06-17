Feature: Un joueur gagne le point


    Scenario: les deux joueurs ont 0 points
    Given Partie avec les deux score a 0 points
    When L'un des joueurs gagne le point 
    Then Le gagnant passe de 0 a 15
    And L'autre ne change pas de score.
