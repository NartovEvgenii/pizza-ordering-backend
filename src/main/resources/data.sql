INSERT INTO pizza (id_pizza, title, description, price, image_path) VALUES
            (1, 'Margarita', 'Medium | Cheese , onion, and tomato pure', 12, 'margarita.png'),
            (2, 'Classic Pepporoni', 'Medium | Cheese, hungarian pepper, paneer, capsicum and onion', 12, 'classicPepporoni.png'),
            (3, 'Chicken Supreme', 'Medium | Cheese , chicken, and  tomato pure', 12, 'chickenSupreme.png'),
            (4, 'Vegeterian', 'Medium | Cheese , vegetables, and tomato pure', 12, 'vegeterian.png')
ON CONFLICT (id_pizza)
DO NOTHING;


WITH TB_CITY (value) AS (
	SELECT 'Voronezh' union all
	SELECT 'Moscow'
	),
	TB_STREETS (value) AS (
	SELECT 'Glory Ave.' union all
	SELECT 'per. Stalin' union all
	SELECT 'Сthulhu street' union all
	SELECT 'Beelzebub boulevard'
	)
INSERT INTO address (id_address, title)
    SELECT ROW_NUMBER() OVER (), concat(city.value,', ',street.value, ', ',house.*, ', ',apartment.*)
      from TB_CITY city
        left join TB_STREETS street on 1 = 1
        left join generate_series(1,10) house on 1 = 1
        left join generate_series(1,100) apartment on 1 = 1
ON CONFLICT (id_address)
DO NOTHING;