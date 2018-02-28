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
INSERT INTO app_widget (app_id, rule,type)
VALUES (1, '{placeHolder:"#__APP_NAME__#",targetFile:"app/src/main/res/values/strings.xml",label:"app名称"}',1);
DELETE FROM app_widget
WHERE app_id = 2;
COMMIT;