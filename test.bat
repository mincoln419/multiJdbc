if exist c:\data-migration\excl (
    echo "Aleady Directory Exists..."
) else (
    mkdir c:\data-migration\excl
    java -jar data-migration-0.0.1-SNAPSHOT.jar %ws_id% %delivery_date%
)

set /p ws_id=Insert WS ID :
set /p delivery_date=Insert Delivery Date(YYYY-MM-DD):

echo Your Info Inserted... WS : %ws_id% , Delivery_Date : %delivery_date%

mklink /D c:\data-migration\excl
