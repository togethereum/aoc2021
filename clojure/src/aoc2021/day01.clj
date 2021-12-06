(ns aoc2021.day01)

(defn solve-1
   [lines]
   (->> lines
       (map read-string)
       (reduce (fn [[sum prev] cur]
                  (if (> cur prev)
                     [(inc sum) cur]
                     [sum cur]))
               [-1 0])
       first))

(def expected-1 7)

(defn solve-2 [lines])