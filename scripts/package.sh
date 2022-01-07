# Packages a folder for submission
# author: Quinlan

DIR=$1

if [ ! -d "./src" ]; then
	cd ..
fi
if [ ! -d "./submissions" ]; then
	mkdir ./submissions
fi
if [ -f "./submissions/${DIR}.zip" ]; then
	rm -v ./submissions/${DIR}.zip
fi

cd ./src

if [ -d "./${DIR}" ]; then
	zip -r ../submissions/${DIR} ./${DIR}/*
else
	echo "Robot ${DIR} does not exist"
fi
