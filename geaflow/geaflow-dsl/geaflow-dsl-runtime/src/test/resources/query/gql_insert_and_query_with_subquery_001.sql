set geaflow.dsl.window.size = 1;
CREATE TABLE modern_vertex (
  id varchar,
  type varchar,
  name varchar,
  other varchar
) WITH (
  type='file',
  geaflow.dsl.file.path = 'resource:///data/modern_vertex.txt'
);

CREATE TABLE modern_edge (
  srcId bigint,
  targetId bigint,
  type varchar,
  weight double
) WITH (
  type='file',
  geaflow.dsl.file.path = 'resource:///data/modern_edge.txt'
);

CREATE TABLE request (
  id int,
  name varchar
) WITH (
  type='file',
  geaflow.dsl.file.path = 'resource:///data/request_with_repeat.txt'
);

CREATE TABLE tbl_result (
  name varchar,
  a_id bigint,
  weight double,
  label varchar,
  b_id bigint,
  b_out_cnt int
) WITH (
	type='file',
	geaflow.dsl.file.path='${target}'
);

INSERT INTO dy_modern.person
SELECT cast(id as bigint), name, cast(other as int) as age
FROM modern_vertex WHERE type = 'person'
;

INSERT INTO dy_modern.software
SELECT cast(id as bigint), name, other as lang
FROM modern_vertex WHERE type = 'software'
;

INSERT INTO dy_modern.knows
SELECT srcId, targetId, weight
FROM modern_edge WHERE type = 'knows'
;

INSERT INTO dy_modern.created
SELECT srcId, targetId, weight
FROM modern_edge WHERE type = 'created'
;

USE GRAPH dy_modern;

INSERT INTO tbl_result
SELECT
  name,
	a_id,
	weight,
	label,
	b_id,
	b_out_cnt
FROM (
  WITH p AS (
      SELECT * FROM request where name is not null
    )
  MATCH (a where id = p.id) -[e]->(b)
  Let b.out_cnt = COUNT((b) -> (c) => c.id)
  RETURN p.name as name, a.id as a_id, e.weight as weight,
  label(e) as label, b.id as b_id, b.out_cnt as b_out_cnt
)
;