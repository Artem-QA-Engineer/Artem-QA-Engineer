1. Выбать имена и возраст всех людей, проживающих в Казани, из таблицы person. <br>

SELECT name, age <br>
FROM person <br>
WHERE address = 'Kazan'; <br>

2. Выбрать имена и возраст всех женщин, проживающих в Казани, из таблицы person, и отсортировать результат по именам в алфавитном порядке.<br>

SELECT name, age <br>
FROM person <br>
WHERE address = 'Kazan' AND gender = 'female'<br>
ORDER BY name;<br>

3. Выбрать уникальные id посетителей, которые либо посещали любую пиццерию с 6 по 9 января 2022 года, либо посещали пиццерию с id 2 (в любое время), и отсортировать их по убыванию.<br>

SELECT DISTINCT person_id <br>
FROM person_visits <br>
WHERE (visit_date BETWEEN DATE '2022-01-06' AND '2022-01-09') OR (pizzeria_id = 2)<br>
ORDER BY person_id DESC;<br>

4. Объединить все записи из таблиц menu и person в один список, выводя для каждой строки id и имя объекта (название пиццы или имя человека), после чего отсортировать результат по id, а затем по имени объекта.<br>

SELECT id AS object_id, pizza_name AS object_name<br>
FROM menu<br>
UNION ALL<br>
SELECT id AS object_id, name AS object_name<br>
FROM person<br>
ORDER BY object_id, object_name;<br>

5. Найти даты и id людей, которые в один и тот же день одновременно делали заказ (в пиццерии) и совершали визит (в пиццерию), после чего сортирует результат по дате (по возрастанию) и по id человека (по убыванию).<br>

SELECT order_date AS action_date, person_id<br>
FROM person_order<br>
INTERSECT<br>
SELECT visit_date AS action_date, person_id<br>
FROM person_visits<br>
ORDER BY action_date, person_id DESC;<br>

6. Добавить в таблицу menu новую запись: пиццу "greek pizza" с ценой 800, id 19, принадлежащую пиццерии с id 2.<br>

INSERT INTO menu (id, pizzeria_id, pizza_name, price)<br>
VALUES (19, 2, 'greek pizza', 800);<br>

7. Снизить цену пиццы "greek pizza" в таблице menu на 10%. <br>

UPDATE menu<br>
SET price = price * 0.9<br>
WHERE pizza_name = 'greek pizza';<br>

8. Удалить все записи о пицце "greek pizza" из таблицы menu.<br>

DELETE FROM menu<br>
WHERE pizza_name = 'greek pizza';<br>

9. Вычислить общую сумму цен всех пицц в меню пиццерии с id 1.<br>

SELECT SUM(price) AS total<br>
FROM menu<br>
WHERE pizzeria_id = 1;<br>

10. Вывести названия всех пиццерий, которые никто ни разу не посетил.<br>

SELECT name<br>
FROM pizzeria<br>
WHERE id NOT IN (<br>
	SELECT pizzeria_id<br>
	FROM person_visits<br>
);<br>

11. Вывести названия и рейтинги пиццерий, которые никто никогда не посещал.<br>

SELECT name, rating <br>
FROM pizzeria<br>
LEFT JOIN person_visits ON pizzeria.id = person_visits.pizzeria_id<br>
WHERE person_visits.pizzeria_id IS NULL;<br>

12. Вывести названия пицц (mushroom pizza и pepperoni pizza), названия пиццерий, где они продаются, и их цены, с сортировкой по названию пиццы, а затем по названию пиццерии.<br>

SELECT menu.pizza_name, pizzeria.name AS pizzeria_name, menu.price<br>
FROM pizzeria<br>
JOIN menu ON menu.pizzeria_id = pizzeria.id WHERE pizza_name = 'mushroom pizza' OR pizza_name = 'pepperoni pizza'<br>
ORDER BY pizza_name, pizzeria_name;<br>
