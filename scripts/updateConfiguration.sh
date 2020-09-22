#cd /home/distareza/Programming/docker/cqrs

#echo "Begin Create User Test"
#docker exec -it oracle-12c bash -c "source /home/.bashrc; echo \"ALTER SESSION SET \\\"_ORACLE_SCRIPT\\\"=true; CREATE USER test IDENTIFIED BY test; GRANT ALL PRIVILEGES TO test\" | sqlplus sys/Oradoc_db1 as sysdba "

echo "Copy configuration schema"

rm -Rf mymusic
docker cp solr_one:/opt/solr/server/solr/configsets/_default .
mv _default mymusic
yes | cp  managed-schema mymusic/conf/

echo "Update Schema Locally"
docker cp mymusic solr_one:/opt/solr/server/solr/configsets

echo "Upload Config to Zookeeper"
docker exec -it --user solr solr_one bin/solr zk -upconfig -n mymusic -z localhost:9983 -d server/solr/configsets/mymusic/conf

echo "Create new Collection"
curl -i "http://localhost:8983/solr/admin/collections?action=CREATE&name=mymusic&numShards=2&replicationFactor=2&maxShardsPerNode=2&collection.configName=mymusic"


