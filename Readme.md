# Algorithme de bowling

## Parser l'input

L'input attendu est de type String, du coup la premiere chose a faire c'est de separer les resultat de chaque Frame.
Ensuite, Convertir ces resultat en type Integer

## Calcul des scores

Le calcul des scores se fasse comme suit:
- Si le joueur n'a eu que des strikes, le total des points est de 300, c'est a dire 60 a chaque Frame
- Si le joueur a eu un Strike au premier ou au milieux des essais, le joueur obtien 15 point plus la somme des trois essais suivants
- Si le joueur a eu un Spare au premier ou au milieux des essais, le joueur obtien 15 point plus la somme des deux essais suivants
- Si le joueur a eu un Strike au dernier coup, le joueur obtien 15 point plus la somme des trois essais suivants, c'est a dire que le joueur a droit a trois balle supplementaires
- Si le joueur a eu un Spare au dernier coup, le joueur obtien 15 point plus la somme des deux essais suivants, c'est a dire que le joueur a droit a deux balle supplementaires
- Les point s'accumulent sauf pour les 5 Strikes du premier condition
