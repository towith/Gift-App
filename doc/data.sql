CREATE OR REPLACE FUNCTION category_val(_name VARCHAR(10))
  RETURNS INTEGER AS $$
BEGIN
  RETURN CASE _name
         WHEN 'test'
           THEN 1 END;
END
$$
LANGUAGE plpgsql;

INSERT INTO app_ (category_, template_path) VALUES (category_val('test'), 'test');
INSERT INTO app_widget (app_id, rule) VALUES (1, '{type:1,placeHolder:"#__APP_NAME__#",label:"app名称"}');
DELETE from app_widget where app_id=1;
commit;