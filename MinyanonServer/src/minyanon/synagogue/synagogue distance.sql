DELIMITER//
  DROP PROCEDURE IF EXISTS geodist//
  CREATE PROCEDURE geodist (IN mylat double, IN mylon double, IN dist int)
  BEGIN
  declare lon1 float; declare lon2 float;declare lat1 float; declare lat2 float;
  -- calculate lon and lat for the rectangle:
  set lon1 = mylon-dist/abs(cos(radians(mylat))*69);set lon2 = mylon+dist/abs(cos(radians(mylat))*69);set lat1 = mylat-(dist/69); set lat2 = mylat+(dist/69);
  -- run the query:
  SELECT syn.*, 6371 * 2 * ASIN(SQRT( POWER(SIN((mylat - syn.latitude) * pi()/180 / 2), 2) +COS(mylat * pi()/180) * COS(syn.latitude * pi()/180) *POWER(SIN((mylon - syn.longtitude) * pi()/180 / 2), 2) )) as distance 
  FROM synagogue syn
  where syn.longtitude between lon1 and lon2 and syn.latitude between lat1 and lat2 
  having distance <= dist ORDER BY Distance;
  END
//DELIMITER ;