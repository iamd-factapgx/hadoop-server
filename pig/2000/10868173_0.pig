REGISTER /usr/hadoop/hadoop/share/hadoop/mapreduce//mongo-java-driver.jar
REGISTER /usr/hadoop/hadoop/share/hadoop/mapreduce//mongo-hadoop-core.jar
REGISTER /usr/hadoop/hadoop/share/hadoop/mapreduce//mongo-hadoop-pig.jar
elements = LOAD '/pubmed/analytics/2000/10868173_0' USING PigStorage() AS (id:chararray);
STORE elements INTO  'mongodb://192.168.1.23:27017/mongodb.telecomnancy.univ-lorraine.fr.statistics' USING com.mongodb.hadoop.pig.MongoUpdateStorage('{_id: "\$id"}', '{\$addToSet: {publications:  "10868173_0" }}', 'id:chararray', '', '{upsert:true}');