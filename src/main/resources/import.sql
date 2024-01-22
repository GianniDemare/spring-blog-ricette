-- INSERT CATEGORIE
INSERT INTO categorie (name) VALUES('Antipasto');
INSERT INTO categorie (name) VALUES('Primo Piatto');
INSERT INTO categorie (name) VALUES('Secondo Piatto');
INSERT INTO categorie (name) VALUES('Contorno');
INSERT INTO categorie (name) VALUES('Dessert');

-- INSERT RICETTA
INSERT INTO ricette (categoria_id, number_of_portions, preparation_time, image, ingredients, title, recipe_text) VALUES( 1, 1, 40, 'https://www.foodelicacy.com/wp-content/uploads/2020/12/spaghetti-bolognese-plated-4.jpg', '1 cipolla piccola 1/2 costa di sedano 1 carota piccola 150 g di pancetta tesa macinata 250 g di lombo di maiale macinato 50 g di lombo di maiale macinato', 'Bolognese', 'Iniziate a preparare il ragù alla bolognese dal soffritto. Tritate finemente al coltello il sedano, la carota e la cipolla. Scaldate l’olio e il burro in una casseruola dal fondo pesante, aggiungete le verdure e fatele appassire lentamente a fiamma bassa. Aggiungete la pancetta e, dopo un paio di minuti, la carne macinata. Rosolate a fiamma alta mescolando continuamente e sgranando con un cucchiaio. Sfumate con il vino bianco e lasciate evaporare. Aggiungete il latte e lasciate assorbire a fiamma media sempre mescolando di tanto in tanto. Aggiustate di sale e pepe fresco di mulinello.');
