DROP TABLE IF EXISTS to_do_item;
 
CREATE TABLE to_do_item (
  id bigint PRIMARY KEY auto_increment,
  text VARCHAR(50) NOT NULL,
  is_completed boolean,
  created_at timestamp with time zone
);


INSERT INTO to_do_item (text, is_completed, created_at) VALUES
  ('safsdghsfhdjtydk', true, '2019-08-26T22:25:58.735Z'),
  ('bdshdhjfgjfoipiopjfg', false, '2019-08-26T22:25:58.735Z');