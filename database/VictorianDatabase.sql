--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: konto; Type: TABLE; Schema: public; Owner: adminfranek
--

CREATE TABLE public.konto (
    id integer NOT NULL,
    login character varying NOT NULL,
    haslo character varying NOT NULL,
    imie character varying,
    nazwisko character varying,
    rola character varying
);


ALTER TABLE public.konto OWNER TO adminfranek;

--
-- Name: konto_id_seq; Type: SEQUENCE; Schema: public; Owner: adminfranek
--

CREATE SEQUENCE public.konto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.konto_id_seq OWNER TO adminfranek;

--
-- Name: konto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: adminfranek
--

ALTER SEQUENCE public.konto_id_seq OWNED BY public.konto.id;


--
-- Name: procedura; Type: TABLE; Schema: public; Owner: adminfranek
--

CREATE TABLE public.procedura (
    id_zamowienia integer NOT NULL,
    adres text,
    email character varying,
    telefon character varying,
    data_zamowienia character varying,
    gotowosc_zamowienia boolean,
    id_wysylki integer,
    oplata boolean,
    data_wysylki character varying,
    nick character varying
);


ALTER TABLE public.procedura OWNER TO adminfranek;

--
-- Name: procedura_id_zamowienia_seq; Type: SEQUENCE; Schema: public; Owner: adminfranek
--

CREATE SEQUENCE public.procedura_id_zamowienia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.procedura_id_zamowienia_seq OWNER TO adminfranek;

--
-- Name: procedura_id_zamowienia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: adminfranek
--

ALTER SEQUENCE public.procedura_id_zamowienia_seq OWNED BY public.procedura.id_zamowienia;


--
-- Name: produkt; Type: TABLE; Schema: public; Owner: adminfranek
--

CREATE TABLE public.produkt (
    id_produktu integer NOT NULL,
    nazwa character varying,
    kategoria character varying,
    podatek_vat integer,
    stan_magazynu integer,
    cena double precision,
    typ integer,
    ryczalt double precision
);


ALTER TABLE public.produkt OWNER TO adminfranek;

--
-- Name: produkt_id_seq; Type: SEQUENCE; Schema: public; Owner: adminfranek
--

CREATE SEQUENCE public.produkt_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produkt_id_seq OWNER TO adminfranek;

--
-- Name: produkt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: adminfranek
--

ALTER SEQUENCE public.produkt_id_seq OWNED BY public.produkt.id_produktu;


--
-- Name: rola; Type: TABLE; Schema: public; Owner: adminfranek
--

CREATE TABLE public.rola (
    id integer NOT NULL,
    rola character varying
);


ALTER TABLE public.rola OWNER TO adminfranek;

--
-- Name: wysylka; Type: TABLE; Schema: public; Owner: adminfranek
--

CREATE TABLE public.wysylka (
    id_wysylki integer NOT NULL,
    wysylka character varying,
    cena double precision
);


ALTER TABLE public.wysylka OWNER TO adminfranek;

--
-- Name: zamowienie; Type: TABLE; Schema: public; Owner: adminfranek
--

CREATE TABLE public.zamowienie (
    id_zamowienia integer NOT NULL,
    id_produktu integer NOT NULL,
    ilosc integer,
    data_danych character varying,
    data_projektu character varying,
    data_zatwierdzenia character varying,
    data_wydrukowania character varying,
    data_wykonania character varying,
    gotowosc boolean
);


ALTER TABLE public.zamowienie OWNER TO adminfranek;

--
-- Name: konto id; Type: DEFAULT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.konto ALTER COLUMN id SET DEFAULT nextval('public.konto_id_seq'::regclass);


--
-- Name: procedura id_zamowienia; Type: DEFAULT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.procedura ALTER COLUMN id_zamowienia SET DEFAULT nextval('public.procedura_id_zamowienia_seq'::regclass);


--
-- Name: produkt id_produktu; Type: DEFAULT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.produkt ALTER COLUMN id_produktu SET DEFAULT nextval('public.produkt_id_seq'::regclass);


--
-- Data for Name: konto; Type: TABLE DATA; Schema: public; Owner: adminfranek
--

COPY public.konto (id, login, haslo, imie, nazwisko, rola) FROM stdin;
3	admin	admin123	Szef	Szefów	ADMINISTRATOR
1	adminfranek	cafeLungo	Franciszek	Czana	ADMINISTRATOR
25	user	password	Foo	User	EMPLOYEE
\.


--
-- Data for Name: procedura; Type: TABLE DATA; Schema: public; Owner: adminfranek
--

COPY public.procedura (id_zamowienia, adres, email, telefon, data_zamowienia, gotowosc_zamowienia, id_wysylki, oplata, data_wysylki, nick) FROM stdin;
71	Krzysztof Czechnik\\n16 Pułku Ułanów Wielkopolskich 2/55\\n86-134 Grupa	krzysiwona@mail.com	123456789	2019-12-02	\N	4	t		krzysiwona
72	Marek Grzybek\\nPolna 17\\n44-350 Gorzyczki	 mark075@wp.pl	111222333	2019-12-04	\N	3	t		 mark075
73	Joanna Roszko\\nMikołaja Kopernika 28\\n19-100 Mońki	kopernik@onet.eu	123-123-123	2019-12-04	\N	5	t		 Kopernika28
74	KAROLINA GOŁASZEWSKA\\nCzerwony Bór 2/2\\n18-300 ZAMBRÓW	 karolinka234443@wp.pl	987654321	2019-12-04	\N	3	\N		 karolinka234443
75	Imię\\nUlica\\nMiasto	mail@mail.com	123456789	2019-12-04	\N	3	t		klient
76	aaaa\\nbbbb\\nccc\\n	mail	tel	2019-12-09	\N	3	t		testowe
77	aaaa	aaaa	zzzzz	2019-12-09	\N	3	\N		testowe2
78	qqqq	www	eeee	2019-12-09	\N	6	\N		testowe3
79	qwe	asd	zxc	2019-12-09	\N	3	t		testowe5
80	asd\\nzxc\\nasd	aaaa	ssss	2019-12-09	\N	4	\N		testowe6
81	werewr	werew	rwe	2019-12-09	\N	3	\N		wer
82	asdasd			2019-12-09	\N	3	\N		testingexpress
\.


--
-- Data for Name: produkt; Type: TABLE DATA; Schema: public; Owner: adminfranek
--

COPY public.produkt (id_produktu, nazwa, kategoria, podatek_vat, stan_magazynu, cena, typ, ryczalt) FROM stdin;
37	Chrzest Ferrero Roche	podziękowania	0	\N	2.49000000953674316	1	5.5
2	balony różowe	balony	23	300	3.49000000953674316	0	3
38	Koperty niebieskie	dodatki	23	500	0.300000011920928955	0	5.5
7	koperty białe	dodatki	23	3000	0.300000011920928955	0	3
3	personalizowane ze zdjęciem	zaproszenia	0	\N	2.5	1	5.5
4	personalizowane z imieniem i datą	zaproszenia	0	\N	2	2	5.5
47	Pudełka z bakaliami	podziękowania	23	\N	3.5	2	5.5
48	świeca	chrzest	23	\N	20	2	5.5
49	szatka ze zdjęciem	chrzest	23	\N	23	2	5.5
46	Magnesy	podziękowania	23	\N	5	2	5.5
50	kartki świąteczne	święta	23	\N	2.5	2	5.5
51	zaproszenia ze zdjęciem	komunia	23	\N	2	1	5.5
52	różaniec	komunia	23	20	15	0	3
53	kubek ze zdjęciem	podziękowania	23	\N	18	2	5.5
5	biblia niebieska	książki	5	5	12.3900003433227539	0	3
54	dla chrzestnych	zaproszenia	23	\N	5	1	5.5
1	balony niebieskie	balony	23	500	3.49000000953674316	0	3
6	Personalizacja	dodatki	5	\N	5	-1	3
45	Usługa Express	dodatki	5	\N	25	-1	3
44	papier (ryza)	dodatki	23	5	0	0	5.5
\.


--
-- Data for Name: rola; Type: TABLE DATA; Schema: public; Owner: adminfranek
--

COPY public.rola (id, rola) FROM stdin;
1	ADMINISTRATOR
2	EMPLOYEE
3	PRINTER
\.


--
-- Data for Name: wysylka; Type: TABLE DATA; Schema: public; Owner: adminfranek
--

COPY public.wysylka (id_wysylki, wysylka, cena) FROM stdin;
2	Odbiór osobisty pobranie	0
1	Odbiór osobisty	0
3	Kurier DPD	15
4	Kurier DPD Pobranie	20
5	Paczkomat InPost	8.98999977111816406
6	Paczkomat InPost Pobranie	12.4899997711181641
\.


--
-- Data for Name: zamowienie; Type: TABLE DATA; Schema: public; Owner: adminfranek
--

COPY public.zamowienie (id_zamowienia, id_produktu, ilosc, data_danych, data_projektu, data_zatwierdzenia, data_wydrukowania, data_wykonania, gotowosc) FROM stdin;
74	53	2	2019-12-12	2019-12-12	\N	\N	\N	\N
79	37	2	\N	\N	\N	\N	\N	\N
79	4	3	2019-12-11	2019-12-12	\N	\N	\N	\N
77	54	2	2019-12-11	\N	\N	\N	\N	\N
72	4	20	2019-12-12	2019-12-12	2019-12-12	\N	\N	\N
73	3	32	2019-12-12	\N	\N	\N	\N	\N
81	49	3	\N	\N	\N	\N	\N	\N
80	37	2	2019-12-12	2019-12-12	\N	\N	\N	\N
75	2	4	2019-12-09	\N	\N	\N	\N	\N
79	6	1	2019-12-09	\N	\N	\N	\N	\N
80	6	1	2019-12-09	\N	\N	\N	\N	\N
82	45	1		\N	\N	\N	\N	\N
78	54	222	\N	\N	\N	\N	\N	\N
76	37	12	\N	\N	\N	\N	\N	\N
82	37	2	2019-12-12	2019-12-12	\N	\N	\N	\N
73	54	2	2019-12-12	2019-12-12	2019-12-12	\N	\N	\N
78	3	3	2019-12-12	2019-12-12	2019-12-12	\N	\N	\N
80	3	45	2019-12-12	\N	\N	\N	\N	\N
79	3	5	\N	\N	\N	\N	\N	\N
75	49	2	2019-12-12	\N	\N	\N	\N	\N
71	48	1	2019-12-12	2019-12-12	2019-12-12	\N	\N	\N
71	49	1	2019-12-12	2019-12-12	2019-12-12	\N	\N	\N
\.


--
-- Name: konto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: adminfranek
--

SELECT pg_catalog.setval('public.konto_id_seq', 42, true);


--
-- Name: procedura_id_zamowienia_seq; Type: SEQUENCE SET; Schema: public; Owner: adminfranek
--

SELECT pg_catalog.setval('public.procedura_id_zamowienia_seq', 82, true);


--
-- Name: produkt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: adminfranek
--

SELECT pg_catalog.setval('public.produkt_id_seq', 54, true);


--
-- Name: konto konto_login_key; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.konto
    ADD CONSTRAINT konto_login_key UNIQUE (login);


--
-- Name: konto konto_pkey; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.konto
    ADD CONSTRAINT konto_pkey PRIMARY KEY (id);


--
-- Name: procedura procedura_pkey; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.procedura
    ADD CONSTRAINT procedura_pkey PRIMARY KEY (id_zamowienia);


--
-- Name: produkt produkt_pkey; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.produkt
    ADD CONSTRAINT produkt_pkey PRIMARY KEY (id_produktu);


--
-- Name: rola rola_pkey; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.rola
    ADD CONSTRAINT rola_pkey PRIMARY KEY (id);


--
-- Name: rola uk_rola; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.rola
    ADD CONSTRAINT uk_rola UNIQUE (rola);


--
-- Name: wysylka wysylka_pkey; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.wysylka
    ADD CONSTRAINT wysylka_pkey PRIMARY KEY (id_wysylki);


--
-- Name: zamowienie zamowienie_pkey; Type: CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.zamowienie
    ADD CONSTRAINT zamowienie_pkey PRIMARY KEY (id_zamowienia, id_produktu);


--
-- Name: konto fk_rola; Type: FK CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.konto
    ADD CONSTRAINT fk_rola FOREIGN KEY (rola) REFERENCES public.rola(rola);


--
-- Name: procedura procedura_id_wysylki_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.procedura
    ADD CONSTRAINT procedura_id_wysylki_fkey FOREIGN KEY (id_wysylki) REFERENCES public.wysylka(id_wysylki);


--
-- Name: zamowienie zamowienie_id_produktu_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.zamowienie
    ADD CONSTRAINT zamowienie_id_produktu_fkey FOREIGN KEY (id_produktu) REFERENCES public.produkt(id_produktu);


--
-- Name: zamowienie zamowienie_id_zamowienia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: adminfranek
--

ALTER TABLE ONLY public.zamowienie
    ADD CONSTRAINT zamowienie_id_zamowienia_fkey FOREIGN KEY (id_zamowienia) REFERENCES public.procedura(id_zamowienia);


--
-- PostgreSQL database dump complete
--

