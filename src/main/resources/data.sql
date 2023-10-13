TRUNCATE pizza CASCADE;
TRUNCATE address CASCADE;
TRUNCATE order_state CASCADE;
TRUNCATE payment_type CASCADE;
TRUNCATE user_order CASCADE;

INSERT INTO pizza (id_pizza, title, description, price, image_path) VALUES
            (1, 'Margarita', 'Medium | Cheese , onion, and tomato pure', 12, 'margarita.png'),
            (2, 'Classic Pepperoni', 'Medium | Cheese, hungarian pepper, paneer, capsicum and onion', 10, 'classicPepperoni.png'),
            (3, 'Chicken Supreme', 'Medium | Cheese , chicken, and  tomato pure', 11, 'chickenSupreme.png'),
            (4, 'Vegetarian', 'Medium | Cheese , vegetables, and tomato pure', 7, 'vegetarian.png')
ON CONFLICT (id_pizza)
DO NOTHING;

WITH TB_CITY (value) AS (
	SELECT 'Voronezh' union all
	SELECT 'Moscow'
	),
	TB_STREETS (value) AS (
	SELECT 'Glory Ave.' union all
	SELECT 'per. Stalin' union all
	SELECT 'Cthulhu street' union all
	SELECT 'Beelzebub boulevard'
	)
INSERT INTO address (id_address, title)
    SELECT nextval('address_id_address_seq'), concat(city.value,', ',street.value, ', ',house.*, ', ',apartment.*)
      from TB_CITY city
        left join TB_STREETS street on 1 = 1
        left join generate_series(1,1) house on 1 = 1
        left join generate_series(1,10) apartment on 1 = 1
ON CONFLICT (id_address)
DO NOTHING;

INSERT INTO order_state (title, identifier) VALUES
            ('Accepted', 'ACCEPTED'),
            ('Awaiting payment', 'AWAITING_PAYMENT'),
            ('Processed', 'PROCESSED'),
            ('Ready', 'READY'),
            ('Closed', 'CLOSED');

INSERT INTO payment_type (title, identifier) VALUES
            ('Bank', 'BANK'),
            ('Cash', 'CASH');