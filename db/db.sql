--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- Name: bzv_candidates_id_seq; Type: SEQUENCE; Schema: public; Owner: bzv_user
--

CREATE SEQUENCE bzv_candidates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bzv_candidates_id_seq OWNER TO bzv_user;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bzv_candidates; Type: TABLE; Schema: public; Owner: bzv_user; Tablespace: 
--

CREATE TABLE bzv_candidates (
    id integer DEFAULT nextval('bzv_candidates_id_seq'::regclass) NOT NULL,
    peasantid integer NOT NULL,
    name character varying NOT NULL,
    date_elimination timestamp with time zone
);


ALTER TABLE bzv_candidates OWNER TO bzv_user;

--
-- Name: bzv_peasants_id_seq; Type: SEQUENCE; Schema: public; Owner: bzv_user
--

CREATE SEQUENCE bzv_peasants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bzv_peasants_id_seq OWNER TO bzv_user;

--
-- Name: bzv_peasants; Type: TABLE; Schema: public; Owner: bzv_user; Tablespace: 
--

CREATE TABLE bzv_peasants (
    id smallint DEFAULT nextval('bzv_peasants_id_seq'::regclass) NOT NULL,
    year smallint NOT NULL,
    name character varying(32) NOT NULL
);


ALTER TABLE bzv_peasants OWNER TO bzv_user;

--
-- Name: bzv_users_id_seq; Type: SEQUENCE; Schema: public; Owner: bzv_user
--

CREATE SEQUENCE bzv_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bzv_users_id_seq OWNER TO bzv_user;

--
-- Name: bzv_users; Type: TABLE; Schema: public; Owner: bzv_user; Tablespace: 
--

CREATE TABLE bzv_users (
    id integer DEFAULT nextval('bzv_users_id_seq'::regclass) NOT NULL,
    year smallint NOT NULL,
    name character varying(32) NOT NULL,
    email character varying(64) NOT NULL,
    hash character(60) NOT NULL
);


ALTER TABLE bzv_users OWNER TO bzv_user;

--
-- Name: bzv_vote_periods_id_seq; Type: SEQUENCE; Schema: public; Owner: bzv_user
--

CREATE SEQUENCE bzv_vote_periods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bzv_vote_periods_id_seq OWNER TO bzv_user;

--
-- Name: bzv_vote_periods; Type: TABLE; Schema: public; Owner: bzv_user; Tablespace: 
--

CREATE TABLE bzv_vote_periods (
    id integer DEFAULT nextval('bzv_vote_periods_id_seq'::regclass) NOT NULL,
    year smallint NOT NULL,
    dateStart timestamp with time zone NOT NULL,
    dateEnd timestamp with time zone NOT NULL,
    dateReference timestamp with time zone,
    voteCount smallint NOT NULL
);


ALTER TABLE bzv_vote_periods OWNER TO bzv_user;

--
-- Name: bzv_vote_types_id_seq; Type: SEQUENCE; Schema: public; Owner: bzv_user
--

CREATE SEQUENCE bzv_vote_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bzv_vote_types_id_seq OWNER TO bzv_user;

--
-- Name: bzv_vote_types; Type: TABLE; Schema: public; Owner: bzv_user; Tablespace: 
--

CREATE TABLE bzv_vote_types (
    id smallint DEFAULT nextval('bzv_vote_types_id_seq'::regclass) NOT NULL,
    name character varying(32) NOT NULL
);


ALTER TABLE bzv_vote_types OWNER TO bzv_user;

--
-- Name: bzv_votes_id_seq; Type: SEQUENCE; Schema: public; Owner: bzv_user
--

CREATE SEQUENCE bzv_votes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bzv_votes_id_seq OWNER TO bzv_user;

--
-- Name: bzv_votes; Type: TABLE; Schema: public; Owner: bzv_user; Tablespace: 
--

CREATE TABLE bzv_votes (
    id integer DEFAULT nextval('bzv_votes_id_seq'::regclass) NOT NULL,
    userid integer NOT NULL,
    candidateid integer NOT NULL,
    votePeriodid integer NOT NULL,
    dateVoted timestamp with time zone NOT NULL,
    voteTypeid smallint NOT NULL
);


ALTER TABLE bzv_votes OWNER TO bzv_user;

--
-- Name: bzv_candidates_pkey; Type: CONSTRAINT; Schema: public; Owner: bzv_user; Tablespace: 
--

ALTER TABLE ONLY bzv_candidates
    ADD CONSTRAINT bzv_candidates_pkey PRIMARY KEY (id);


--
-- Name: bzv_peasants_pkey; Type: CONSTRAINT; Schema: public; Owner: bzv_user; Tablespace: 
--

ALTER TABLE ONLY bzv_peasants
    ADD CONSTRAINT bzv_peasants_pkey PRIMARY KEY (id);


--
-- Name: bzv_users_pkey; Type: CONSTRAINT; Schema: public; Owner: bzv_user; Tablespace: 
--

ALTER TABLE ONLY bzv_users
    ADD CONSTRAINT bzv_users_pkey PRIMARY KEY (id);


--
-- Name: bzv_vote_periods_pkey; Type: CONSTRAINT; Schema: public; Owner: bzv_user; Tablespace: 
--

ALTER TABLE ONLY bzv_vote_periods
    ADD CONSTRAINT bzv_vote_periods_pkey PRIMARY KEY (id);


--
-- Name: bzv_vote_types_pkey; Type: CONSTRAINT; Schema: public; Owner: bzv_user; Tablespace: 
--

ALTER TABLE ONLY bzv_vote_types
    ADD CONSTRAINT bzv_vote_types_pkey PRIMARY KEY (id);


--
-- Name: bzv_votes_pkey; Type: CONSTRAINT; Schema: public; Owner: bzv_user; Tablespace: 
--

ALTER TABLE ONLY bzv_votes
    ADD CONSTRAINT bzv_votes_pkey PRIMARY KEY (id);


--
-- Name: bzv_votes_userid_candidateid_vote_periodid_key; Type: CONSTRAINT; Schema: public; Owner: bzv_user; Tablespace: 
--

ALTER TABLE ONLY bzv_votes
    ADD CONSTRAINT bzv_votes_userid_candidateid_vote_periodid_key UNIQUE (userid, candidateid, votePeriodid);


--
-- Name: bzv_candidates_peasantid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bzv_user
--

ALTER TABLE ONLY bzv_candidates
    ADD CONSTRAINT bzv_candidates_peasantid_fkey FOREIGN KEY (peasantid) REFERENCES bzv_peasants(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: bzv_votes_candidateid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bzv_user
--

ALTER TABLE ONLY bzv_votes
    ADD CONSTRAINT bzv_votes_candidateid_fkey FOREIGN KEY (candidateid) REFERENCES bzv_candidates(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: bzv_votes_typeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bzv_user
--

ALTER TABLE ONLY bzv_votes
    ADD CONSTRAINT bzv_votes_votetypeid_fkey FOREIGN KEY (voteTypeid) REFERENCES bzv_vote_types(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: bzv_votes_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bzv_user
--

ALTER TABLE ONLY bzv_votes
    ADD CONSTRAINT bzv_votes_userid_fkey FOREIGN KEY (userid) REFERENCES bzv_users(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: bzv_votes_vote_periodid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bzv_user
--

ALTER TABLE ONLY bzv_votes
    ADD CONSTRAINT bzv_votes_vote_periodid_fkey FOREIGN KEY (votePeriodid) REFERENCES bzv_vote_periods(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

