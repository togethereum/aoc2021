(ns aoc2021.day01
    (:require [clojure.java.io :as io]))

(defn read-lines
   [fname]
   (-> fname io/resource io/file io/reader line-seq))

(defn solve
   [fname]
   (->> fname
       read-lines
       (map read-string)
       (reduce (fn [[sum prev] cur]
                  (if (> cur prev)
                     [(inc sum) cur]
                     [sum cur]))
               [-1 0])
       first))