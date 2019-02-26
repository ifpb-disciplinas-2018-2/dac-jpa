/* Author:  Ricardo Job
 * Created: 25/02/2019
 */
/* Executando operação simples */
CREATE OR REPLACE FUNCTION somar(
    IN primeiro double precision,
    IN segundo double precision,
    OUT soma double precision)
  RETURNS double precision AS
$BODY$
BEGIN
    soma = primeiro + segundo;
END;
$BODY$
  LANGUAGE plpgsql