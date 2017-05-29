--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2017-05-28 21:21:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 187 (class 1255 OID 16397)
-- Name: uuid(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION uuid() RETURNS character varying
    LANGUAGE plpgsql
    AS $$
DECLARE
	output character varying(255);
BEGIN
	SELECT md5(random()::text || clock_timestamp()::text)::uuid INTO output;
	output = replace(output,'-','');
	RETURN output;
END;
$$;


ALTER FUNCTION public.uuid() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 24586)
-- Name: ficha; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ficha (
    id character varying(32) DEFAULT uuid() NOT NULL,
    fuma boolean,
    cuantos_fuma character varying(256),
    oclusion character varying(512),
    bruxismo boolean,
    tipo_bruxismo character varying(32),
    usa_placa boolean,
    miorrelajante boolean,
    cantidad_de_piezas_existentes integer
);


ALTER TABLE ficha OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16416)
-- Name: pacientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pacientes (
    id character varying(32) DEFAULT uuid() NOT NULL,
    nombre_y_apellido character varying(128) NOT NULL,
    dni character varying(12) NOT NULL,
    domicilio character varying(256) DEFAULT 'Domicilio no colocado'::character varying,
    telefono_fijo character varying(22) DEFAULT 'Sin colocar'::character varying,
    telefono_movil character varying(22) DEFAULT 'Sin colocar'::character varying,
    obra_social character varying(128),
    fecha_de_nacimiento date,
    observaciones text
);


ALTER TABLE pacientes OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16447)
-- Name: pieza_dental; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pieza_dental (
    id character varying(32) NOT NULL,
    numero character varying(3) NOT NULL,
    posicion character varying(10) NOT NULL
);


ALTER TABLE pieza_dental OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16452)
-- Name: tipo_tratamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_tratamiento (
    nombre character varying(128) NOT NULL,
    color character varying(32) NOT NULL,
    tipo character varying(32) NOT NULL
);


ALTER TABLE tipo_tratamiento OWNER TO postgres;

--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN tipo_tratamiento.tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN tipo_tratamiento.tipo IS 'A realizar o Realizado';


--
-- TOC entry 185 (class 1259 OID 16463)
-- Name: tratamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tratamiento (
    id_paciente character varying(32) NOT NULL,
    numero_pieza character varying(3) NOT NULL,
    posicion character varying(10) NOT NULL,
    id_tratamiento character varying(32) NOT NULL,
    fecha timestamp with time zone NOT NULL,
    descripcion text,
    nombre_tratamiento character varying(128) NOT NULL
);


ALTER TABLE tratamiento OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16430)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuarios (
    id character varying(32) DEFAULT uuid() NOT NULL,
    username character varying(32) NOT NULL,
    password character varying(32) NOT NULL,
    "group" character varying(32) NOT NULL,
    ultima_conexion timestamp with time zone DEFAULT now()
);


ALTER TABLE usuarios OWNER TO postgres;

--
-- TOC entry 2145 (class 0 OID 24586)
-- Dependencies: 186
-- Data for Name: ficha; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ficha (id, fuma, cuantos_fuma, oclusion, bruxismo, tipo_bruxismo, usa_placa, miorrelajante, cantidad_de_piezas_existentes) VALUES ('a526fd47-8912-4f27-901d-dc15fa33', false, 'No especificado', NULL, false, '', false, false, 0);


--
-- TOC entry 2140 (class 0 OID 16416)
-- Dependencies: 181
-- Data for Name: pacientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pacientes (id, nombre_y_apellido, dni, domicilio, telefono_fijo, telefono_movil, obra_social, fecha_de_nacimiento, observaciones) VALUES ('50946097dbe9d975d07c929ff0fe28cc', 'Pepe Sanchez', '545654', 'Ficticio 123', '379 4265748', '', 'OSDE', '1987-11-14', 'No viene nunca');
INSERT INTO pacientes (id, nombre_y_apellido, dni, domicilio, telefono_fijo, telefono_movil, obra_social, fecha_de_nacimiento, observaciones) VALUES ('4e8c96e8d1dbee9266abdaf9721d3003', 'Juan Perez', '25478541', 'Barrio Ficticio 123', '3782 65869', '', 'OSDE', '2017-05-03', 'Suele llegar tarde');
INSERT INTO pacientes (id, nombre_y_apellido, dni, domicilio, telefono_fijo, telefono_movil, obra_social, fecha_de_nacimiento, observaciones) VALUES ('a526fd47-8912-4f27-901d-dc15fa33', 'Prueba', '12345688', 'Barrio Ficticio 123', '', '', '', '1900-01-01', 'copete');
INSERT INTO pacientes (id, nombre_y_apellido, dni, domicilio, telefono_fijo, telefono_movil, obra_social, fecha_de_nacimiento, observaciones) VALUES ('33dc4ada6d477ffd6fd437a74cf938ee', 'Eduardo Garcia', '12345678', 'Las Piedras 1234', '', '', 'OSDE', '1990-06-07', 'Una pequeña observacion');


--
-- TOC entry 2142 (class 0 OID 16447)
-- Dependencies: 183
-- Data for Name: pieza_dental; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2143 (class 0 OID 16452)
-- Dependencies: 184
-- Data for Name: tipo_tratamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('OBTURACION', 'red', 'REALIZADO');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('PIEZA_EXTRAIDA', '#cc0000', 'REALIZADO');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('CORONA', '#800000', 'REALIZADO');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('ENDODONCIA_REALIZADA', '#330000', 'REALIZADO');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('TRATAMIENTO_DE_CONDUCTO', '#ff8080', 'REALIZADO');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('CARIES', '#99b3ff', 'A REALIZAR');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('ENDODONCIA', '#668cff', 'A REALIZAR');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('PIEZA_NO_ERUPCIONADA', '#1a53ff', 'A REALIZAR');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('PIEZA_A_EXTRAER', '#002db3', 'A REALIZAR');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('CORONA_A_REALIZAR', '#001a66', 'A REALIZAR');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('GINGIVITIS_MARGINAL_CRONICA', '#000d33', 'A REALIZAR');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('PERIODONTITIS_MARGINAL', '#005ce6', 'A REALIZAR');
INSERT INTO tipo_tratamiento (nombre, color, tipo) VALUES ('ABRACIONES', '#99c2ff', 'A REALIZAR');


--
-- TOC entry 2144 (class 0 OID 16463)
-- Dependencies: 185
-- Data for Name: tratamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2141 (class 0 OID 16430)
-- Dependencies: 182
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuarios (id, username, password, "group", ultima_conexion) VALUES ('7796c840ba7780d57780707b7088cd23', 'yogonza524', '123', 'USER', '2017-05-28 21:02:25.88-03');


--
-- TOC entry 2011 (class 2606 OID 16429)
-- Name: dni_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pacientes
    ADD CONSTRAINT dni_unique UNIQUE (dni);


--
-- TOC entry 2025 (class 2606 OID 24594)
-- Name: ficha_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ficha
    ADD CONSTRAINT ficha_pk PRIMARY KEY (id);


--
-- TOC entry 2013 (class 2606 OID 16427)
-- Name: pacientes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pacientes
    ADD CONSTRAINT pacientes_pk PRIMARY KEY (id);


--
-- TOC entry 2019 (class 2606 OID 16451)
-- Name: pieza_dental_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pieza_dental
    ADD CONSTRAINT pieza_dental_pk PRIMARY KEY (id, numero, posicion);


--
-- TOC entry 2021 (class 2606 OID 16456)
-- Name: tratamiento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_tratamiento
    ADD CONSTRAINT tratamiento_pk PRIMARY KEY (nombre, color, tipo);


--
-- TOC entry 2023 (class 2606 OID 16470)
-- Name: trato_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tratamiento
    ADD CONSTRAINT trato_pk PRIMARY KEY (id_paciente, numero_pieza, posicion, id_tratamiento);


--
-- TOC entry 2015 (class 2606 OID 16438)
-- Name: username_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT username_unique UNIQUE (username);


--
-- TOC entry 2017 (class 2606 OID 16436)
-- Name: usuarios_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT usuarios_pk PRIMARY KEY (id);


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-05-28 21:21:51

--
-- PostgreSQL database dump complete
--

