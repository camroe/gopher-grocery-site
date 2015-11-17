LOCK TABLES gophergroceries.category WRITE;
INSERT INTO gophergroceries.category VALUES 
(95,'Baking','#'),
(96,'Baking & Cooking Needs','#'),
(97,'Beer/Wine/Spirits','#'),
(98,'Beverages','#'),
(99,'Breakfast & Cereal','#'),
(100,'Canned Goods and Soups','#'),
(101,'Chips/Snacks','#'),
(102,'Condiments and Sauces','#'),
(103,'Dairy','#'),(104,'Deli','#'),
(105,'Frozen','#'),
(106,'Fruits and Vegetables','#'),
(107,'Grain, Pasta and Beans','#'),
(108,'Meat and Seafood','#');
UNLOCK TABLES;

LOCK TABLES gophergroceries.subcategory WRITE;
INSERT INTO gophergroceries.subcategory VALUES 
(96,'Baking Mixes','bakingmixes',96),
(97,'Baking Morsels, Bars & Cocoa','bakingmorsels',96),
(98,'Dough','dough',96),
(99,'Bread','bread',95),
(100,'Buns','buns',95),
(101,'Coffee & Filters','coffee',98),
(102,'Hot Cocoa and Milk Mixes','cocoa',98),
(103,'Juice','juice',98),
(104,'Mixers','mixers',98),
(105,'Seitzer & Tonic','tonic',98),
(106,'Smoothies','smoothies',98),
(107,'Soft Drinks','softdrinks',98),
(108,'Tea','tea',98),
(109,'Sports & Energy Drinks','sports',98),
(110,'Water','water',98),
(111,'Bagels','bagels',95),
(112,'Bakery Bread','bakerybread',95),
(113,'Desserts & Pastries','desserts',95),
(114,'English Muffins','english',95),
(115,'Muffins / Coffee Cakes','muffins',95),
(116,'Pitas, Tortillas, Taco Shells & Wraps','pita',95),
(117,'Pizza Crusts','pizzacrusts',95),
(118,'Light Beer','lightbeer',97);
UNLOCK TABLES;