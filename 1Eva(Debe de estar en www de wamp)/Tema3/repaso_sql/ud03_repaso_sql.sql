INSERT INTO `pujas` (`id`, `id_item`, `id_user`, `cantidad`, `fecha`) VALUES
			(3, 1, 1, 2, '2020-10-19'),
			(4, 2, 2, 3, '2020-10-19');
            INSERT INTO `pujas` (`id`, `id_item`, `id_user`, `cantidad`, `fecha`) VALUES
			(5, 1, 1, 2, '2019-10-19'),
			(6, 2, 2, 3, '2019-10-19');
            
		select count(*), AVG(cantidad) from pujas where fecha < '2020/10/10';

INSERT INTO items(`id`,`id_cat`,`id_user`,	`nombre`,	`preciopartida`,	`descripcion`,	`fechafin`)
	VALUES 	(7,	1,	1,	'monstera',	150,	'Planta de interior',	'2023/01/01'),
    (8,	1,	1,	'geranio',	150,	'Planta de interior',	'2023/01/01'),
    (9,	1,	1,	'Cala',	150,	'Planta de interior',	'2023/01/01');
    
            INSERT INTO `pujas` (`id`, `id_item`, `id_user`, `cantidad`, `fecha`) VALUES
			(11, 6, 2, 150000, '2021-10-10'),
            (12, 5, 1, 600000, '2021-11-11'),
            (13, 6, 1, 15000, '2021-10-16'),
			(14,5, 2,6000, '2021-10-17');


-- o	Nombres de las imágenes de ítems de la categoría ‘X’

		select imagen from imagenes where exists (select id from items where id_item= id and exists
        (select id_cat from categorias where categoria='flores' and id=id_cat));
        
        
		select imagen from imagenes inner join items on items. id=imagenes.id
						inner join categorias on categorias.id=items.id
                        where categoria like 'flores';
              
              
-- o	Nombre e email de los usuarios que han pujado por ítems con un precio de partida entre 1000 y 5000
		-- Nombre e email de los usuarios relacionados con ítems con un precio de partida entre 1000 y 5000

		select nombre, email from usuarios where exists ( select id from items where preciopartida >1000 and preciopartida <5000);
		
        select id,nombre, email from usuarios where exists 
        ( select id from items where preciopartida between 1000 and 5000);
        
        -- Nombre e email de los usuarios que han pujado por ítems con un precio de partida entre 1000 y 5000
		select nombre, email from usuarios where exists 
        ( select id from pujas where usuarios.id=pujas.id_user 
        and exists(select id from items where preciopartida >1000 and preciopartida <5000 and items.id_user=usuarios.id));
        
        
        select usuarios.nombre, usuarios.email from usuarios 
							  inner join pujas on usuarios.id=pujas.id_user
							  inner join items on items.id_user=pujas.id_user
                              where items.preciopartida between 1000 and 5000;
                              
-- o	Fecha, nombre de usuario y nombre de ítem de la última puja

		select Max(fecha), usuarios.nombre, items.nombre from pujas, usuarios, items 
								where usuarios.id=pujas.id_user
                                and items.id=pujas.id_item;
                                
        select  Max(fecha),usuarios.username, usuarios.nombre, items.nombre, pujas.fecha  
        from usuarios inner join pujas on usuarios.id=pujas.id_user
		inner join items on items.id_user=pujas.id_user
		order by pujas.fecha desc;

-- o	Cantidad de usuarios que tienen algún ítem, pero ninguna puja

		select count(*) from usuarios where exists (select * from items where usuarios.id=items.id_user and not exists (select * from pujas where pujas.id_item=items.id));
        
        select * from usuarios inner join items on usuarios.id=items.id_user
									  inner join pujas on pujas.id_item= items.id
                                      where pujas.id is not null;
                                      
                                      
-- o	Por cada fecha anterior al ‘2020/10/10’ en la que haya pujas, cuántas pujas hay y valor medio de éstas
        
		select fecha fechaPuja, AVG(cantidad) precio , (select count(*) from pujas where fecha = fechaPuja ) numPujas from pujas
        where fecha < '2020-10-20'
        group by fecha, numPujas;
        
-- o	Valor más alto pujado y a qué ítem (nombre) corresponde
        
		-- Producto cartesiano
		select Max(pujas.cantidad), items.nombre from pujas, items
        where pujas.id_item=items.id;
        
        -- INNER Join
        select Max(pujas.cantidad), items.nombre from pujas 
        inner join items on pujas.id_item=items.id;

-- o	Nombre de los ítems de categoría ‘X’, junto a cantidad media pujada por cada uno

		-- Producto cartesiano
		select items.nombre, AVG(pujas.cantidad) from items, pujas, categorias
        where categorias.id=items.id_cat and categorias.categoria= 'flores'
        and items.id=pujas.id_item;
        
        -- INNER Join
        select avg(pujas.cantidad), items.nombre from pujas 
        inner join items on pujas.id_item=items.id
		inner join categorias on categorias.id=items.id_cat
        where categorias.categoria = 'flores' ;

-- o	Nombre y precio de partida de los ítems que no tienen ninguna imagen y tienen más de 3 pujas
        
        select items.id,items.nombre, items.preciopartida from items 
        where not exists (select * from imagenes where imagenes.id_item=items.id)
        and 2 < (Select count(*) from pujas where pujas.id_item=items.id);
        
        -- INNER JOIN
		select items.id,items.nombre, items.preciopartida from items left join imagenes on items.id = imagenes.id_item
							where imagenes.id is null and
                            (select count(id) from pujas  where pujas.id_item=items.id group by id_item )>2;
                            
-- o	Nombres de las categorías en las que hay al menos 3 subastas vigentes

		select categorias.categoria from categorias where 3< (select count(*) from items 
        where categorias.id=items.id_cat and items.fechafin> sysdate());

-- o	Nombre y descripción de los ítems cuya máxima puja al menos duplica el precio de partida

		select items.nombre, items.descripcion from items 
        where preciopartida* 2 < (select Max(cantidad) from pujas where pujas.id_item=items.id);
