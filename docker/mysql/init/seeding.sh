#!/bin/bash

set -e

db_sqls=(
  "hotel/origin.sql"
  "hotel/changes.sql"
)

sqlExecute() {
	path=$1
	shift
	sqls=("${@}")

	for file in "${sqls[@]}"
	do
		echo "- import: /$path/$file"
		mysql --default-character-set=utf8 -uroot -p${MYSQL_ROOT_PASSWORD} < "/$path/$file"
	done
}

sqlExecute "init-db" "${db_sqls[@]}"
