TRUNCATE pizza CASCADE;
TRUNCATE address CASCADE;
TRUNCATE order_state CASCADE;
TRUNCATE payment_type CASCADE;
TRUNCATE user_order CASCADE;
TRUNCATE mobile_user CASCADE;
TRUNCATE user_order_history CASCADE;
ALTER SEQUENCE address_id_address_seq RESTART WITH 1;
ALTER SEQUENCE user_order_id_order_seq RESTART WITH 1;
ALTER SEQUENCE order_item_id_order_item_seq RESTART WITH 1;
ALTER SEQUENCE or_hist_id_or_hist_seq RESTART WITH 1;

INSERT INTO cooking_plan (id_cooking_plan, base_preparation_time, oven_time, packing_time) VALUES
        (1, '0 mins 9.00 secs', '1 mins 0.00 secs', '0 mins 5.00 secs'),
        (2, '0 mins 9.00 secs', '5 mins 0.00 secs', '0 mins 5.00 secs'),
        (3, '0 mins 20.00 secs', '3 mins 0.00 secs', '0 mins 20.00 secs'),
        (4, '0 mins 5.00 secs', '0 mins 5.00 secs', '0 mins 5.00 secs')
ON CONFLICT (id_cooking_plan)
DO NOTHING;

INSERT INTO pizza (id_pizza, title, description, price, image_path, fk_cooking_plan) VALUES
            (1, 'Margarita', 'Medium | Cheese , onion, and tomato pure', 12, 'margarita.png', 1),
            (2, 'Classic Pepperoni', 'Medium | Cheese, hungarian pepper, paneer, capsicum and onion', 10, 'classicPepperoni.png', 2),
            (3, 'Chicken Supreme', 'Medium | Cheese , chicken, and  tomato pure', 11, 'chickenSupreme.png', 3),
            (4, 'Vegetarian', 'Medium | Cheese , vegetables, and tomato pure', 7, 'vegetarian.png', 4)
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

INSERT INTO order_state (id_order_state, title, identifier) VALUES
            (1, 'Accepted', 'ACCEPTED'),
            (2, 'Awaiting payment', 'AWAITING_PAYMENT'),
            (3, 'Processed', 'PROCESSED'),
            (4, 'Ready', 'READY'),
            (5, 'Closed', 'CLOSED');

INSERT INTO payment_type (id_payment_type, title, identifier) VALUES
            (1, 'Bank', 'BANK'),
            (2, 'Cash', 'CASH');

INSERT INTO mobile_user (id_user, email, name, password, surname, fk_general_address) VALUES
            (1, 'defaultUser@gmail.com', 'DefaultName', 'passwordHash', 'DefaultSurname', 1);
            
INSERT INTO user_order (id_order, full_price, fk_address, fk_user, fk_order_state, fk_payment_type) VALUES
            (1, 7, 1, 1, 3, 2),
            (2, 14, 1, 1, 3, 2),
            (3, 18, 1, 1, 3, 2);

INSERT INTO order_item (count_items, price, fk_order, fk_pizza) VALUES
            (1, 7, 1, 4),
            (2, 14, 2, 4),
            (1, 11, 3, 3),
            (1, 7, 3, 4);

INSERT INTO user_order_history (change_time, fk_order_state, fk_user_order) VALUES
            (current_timestamp, 3, 1),
            (current_timestamp, 3, 2),
            (current_timestamp, 3, 3),
            (current_timestamp, 1, 1),
            (current_timestamp, 1, 2),
            (current_timestamp, 1, 3);