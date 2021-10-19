drop table if exists public.users cascade;
drop table if exists public.role cascade;
drop table if exists public.category cascade;
drop table if exists public.product cascade;
drop table if exists public.user_role cascade;

drop sequence if exists public.users_id_seq cascade;
drop sequence if exists public.role_id_seq cascade;
drop sequence if exists public.category_id_seq cascade;
drop sequence if exists public.product_id_seq cascade;
drop sequence if exists public.user_role_id_seq cascade;


CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    password character varying(255),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.role (
    id bigint NOT NULL,
    rolename character varying(255),
    isactive boolean,
    description character varying(255),
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE public.category (
    id bigint NOT NULL,
    name character varying(255),
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE public.product (
    id bigint NOT NULL,
    name varchar,
    imagename varchar,
    description varchar,
    categoryid bigint,
    price numeric(10,2),
    weight numeric(10,2),
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_role(
	id bigint NOT NULL,
	userid bigint,
	rolesid bigint,
	CONSTRAINT user_role_pkey PRIMARY KEY (id)
);


ALTER TABLE public.users OWNER TO presideatech;
ALTER TABLE public.role OWNER TO presideatech;
ALTER TABLE public.category OWNER TO presideatech;
ALTER TABLE public.product OWNER TO presideatech;
ALTER TABLE public.user_role OWNER TO presideatech;

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO presideatech;

--
-- Name: authtoken_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: presideatech
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO presideatech;

--
-- Name: authtoken_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: presideatech
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO presideatech;

--
-- Name: authtoken_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: presideatech
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO presideatech;

--
-- Name: authtoken_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: presideatech
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


CREATE SEQUENCE public.user_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_role_id_seq OWNER TO presideatech;

--
-- Name: authtoken_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: presideatech
--

ALTER SEQUENCE public.user_role_id_seq OWNED BY public.user_role.id;





-- public.product foreign keys

ALTER TABLE public.product ADD CONSTRAINT fk_prodcut_category FOREIGN KEY (categoryid) REFERENCES public.category(id);

-- public.user_role foreign keys

ALTER TABLE public.user_role ADD CONSTRAINT fk_userrole_user FOREIGN KEY (userid) REFERENCES public.users(id);
ALTER TABLE public.user_role ADD CONSTRAINT fk_userrole_role FOREIGN KEY (rolesid) REFERENCES public.role(id);




ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);

INSERT INTO public.category(name) values('HeadPhone');  -- 1
INSERT INTO public.category(name) values('Speaker'); --2
INSERT INTO public.category(name) values('Fittness Tracker'); --3
INSERT INTO public.category(name) values('Computer Accessories'); -- 4
INSERT INTO public.category(name) values('Pen Drive'); -- 5
INSERT INTO public.category(name) values('Memory Card'); -- 6
INSERT INTO public.category(name) values('Camera'); --7
INSERT INTO public.category(name) values('Router'); --8
INSERT INTO public.category(name) values('Furniture'); --9
INSERT INTO public.category(name) values('Books'); --10
INSERT INTO public.category(name) values('Chocolate'); --11
INSERT INTO public.category(name) values('Cake'); --12
INSERT INTO public.category(name) values('Snacks'); --13
INSERT INTO public.category(name) values('Breakfast Essentials'); --14
INSERT INTO public.category(name) values('Cooking Oil'); --15
INSERT INTO public.category(name) values('Herbs and Spices'); --16
INSERT INTO public.category(name) values('Mobile'); --17
INSERT INTO public.category(name) values('Laptop'); --18
INSERT INTO public.category(name) values('TV'); --19
INSERT INTO public.category(name) values('Trimmer'); --20
INSERT INTO public.category(name) values('Shoes'); --21
INSERT INTO public.category(name) values('Jacket'); --22



ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);

INSERT INTO public.product(name, imagename,description, categoryid, price, weight) values('boAt Rockerz 255 in-Ear Earphones with 8 Hours Battery, IPX5, Bluetooth V5.0 and Voice Assistant(Neon)', 'boat255Headphone.jpg', 'boAt Rockerz 255, while being lightweight in design pumps out your favourite tunes with powerful HD sound and deep boosted bass
It comes with a battery backup of upto 8 hours. Driver Type : Dynamic. Sweat Proof : Yes
Enjoy smooth connectivity via its Bluetooth 5.0 Technology', 1, 220, 50);


INSERT INTO public.product(name, imagename,description, categoryid, price, weight) values('Samsung Galaxy Ear Buds Pro | Intelligent ANC with 99% Noise Cancellation, Wireless Charging, 28 Hours Playtime | Black', 'samsungEarBuds.jpg', 'True wireless earbuds with Intelligent Active Noise Canceling (ANC) - seamlessly switch between noise canceling and fully adjustable ambient sound.
Voice Detect instantly switches from ANC to Ambient sound when it hears your voice.', 1, 8990, 100);

INSERT INTO public.product(name, imagename,description, categoryid, price, weight) values('Zebronics ZEB-COUNTY Wireless Bluetooth Portable Speaker With Supporting Carry Handle, USB, SD Card, AUX, FM & Call Function. (Green)', 'zebronicsSpeaker.jpg', 'Zeb-county is a compact and handy portable speaker that comes with multi-connectivity options like wireless BT/USB/micro SD and AUX. Wall Mountable Satellite : No
The speaker comes with a call function along with a built-in fm radio too', 2, 5000, 500);




ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);

INSERT INTO role(rolename, isactive) values('ROLE_ADMIN', true) ON CONFLICT(id) DO NOTHING;
INSERT INTO role(rolename, isactive) values('ROLE_USER', true) ON CONFLICT(id) DO NOTHING;

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

INSERT INTO users(firstname, lastname, email, password) values('system', 'admin', 'admin@gmail.com', '$2a$10$jDgXPQAK/PZcXVeMkuyT2upoD3D2EHdUO6SQxPE2NlTOaXLYaX91.') ON CONFLICT(id) DO NOTHING;


ALTER TABLE ONLY public.user_role ALTER COLUMN id SET DEFAULT nextval('public.user_role_id_seq'::regclass);

INSERT INTO user_role(userid, rolesid) values(1, 1) ON CONFLICT(id) DO NOTHING;
INSERT INTO user_role(userid, rolesid) values(1, 2) ON CONFLICT(id) DO NOTHING;




















