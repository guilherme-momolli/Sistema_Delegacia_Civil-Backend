ALTER TABLE public.droga ADD quantidade int NULL;
ALTER TABLE public.droga ALTER COLUMN quantidade TYPE numeric(15, 2) USING quantidade::numeric(15, 2);

ALTER TABLE public.arma ADD COLUMN situacao_arma_fogo varchar(50);
