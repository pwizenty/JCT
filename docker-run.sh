#!/usr/bin/env bash

if [ -z "$1" ]
then
    echo "Missing input. Exiting..."
    exit 1
fi

plugin_p="$1"
plugin_name="$2"
file_path_jolie="$3"

# Build Jolie file path mapping
folder_jolie=$(realpath "$(dirname $file_path_jolie)")
file_name_jolie=$(basename "$file_path_jolie")
absolute_path_jolie="$folder_jolie"/"$file_name_jolie"
mapping_file_jolie="/usr/local/Lemma4Jolie/jolie.ol"

# D3PC Plugin
if [ "$plugin_name" = "D3PC" ]; then
  docker run -v "$absolute_path_jolie":"$mapping_file_jolie" \
    jct:latest \
    "$plugin_p" \
    "$plugin_name" \
    "$mapping_file_jolie"
fi

# LEMMA4Jolie Plugin
# We build a second file mapping to support the LEMMA jolie file
if [ "$plugin_name" = "LEMMA4Jolie" ]; then
  # Build LEMMA file path mapping
  file_path_lemma="$4"
  folder_lemma=$(realpath "$(dirname $file_path_lemma)")
  file_name_lemma=$(basename "$file_path_lemma")
  absolute_path_lemma="$folder_lemma"/"$file_name_lemma"
  mapping_file_lemma="/usr/local/Lemma4Jolie/lemma.ol"
  docker run -v "$absolute_path_jolie":"$mapping_file_jolie" \
    -v "$absolute_path_lemma":"$mapping_file_lemma" \
    jct:latest \
    "$plugin_p" \
    "LEMMA4Jolie={source=$mapping_file_lemma}" \
    "$mapping_file_jolie"
fi