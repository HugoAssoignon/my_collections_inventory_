# My Collection Inventory

MyCollectionInventory is a school project following this [subject](https://android-learning-ap5.web.app/project-isen/#sujet), The goal is to display the biggest amount of element for various collections, for the users to be able to add them to their collections and keep a track on them easily.
We wish to e able to display many differents kinds of collection, such as manga, anime, playing cards (Pokemon, Magic, ...) or others. 

## Developers
 - Assoignon Hugo (HugoAssoignon)
 - Kolaczek Korentin (Korentin-k)
 - Hoest Edouard (EdouardHoest)

## API

In order to use the project, you will need to launch the built api, download it from [github](https://github.com/EdouardHoest/my_collections_inventory_api).

### Requirements: 
 - Java 21
 - Load maven project (mvn clean install in cmd)
 - launch main in MyCollectionInventoryApplication.java
 - Call on [http://localhost:8080/externalManga/load-manga](http://localhost:8080/externalManga/load-manga) (GET) to add manga into your database.
   
## Difficulties

As the api is local and kotlin blocks http calls (not https so not secured),
we needed a specific setup to call the api, that's why, you need to modify 2 files in the mobile app
project:

- DataIp.kt
- network-security-config.xml
_search for "YOUR_IP_ADDRESS" in the project_
