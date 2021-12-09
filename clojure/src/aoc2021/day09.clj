(ns aoc2021.day09
  (:require [clojure.string :as str]))

(defn neighbor-coords
  [m x y]
  (let [coords [(when (< 0 x) [(dec x) y])
                (when (> (count m) (inc x)) [(inc x) y])
                (when (< 0 y) [x (dec y)])
                (when (> (count (first m)) (inc y)) [x (inc y)])]]
    (remove nil? coords)))

(defn neighbors
  [m x y]
  (map #(get-in m %) (neighbor-coords m x y)))

(defn low-points
  [m]
  (remove nil?
    (for [i (range (count m))
          j (range (count (first m)))]
      (let [val (get-in m [i j])
            min-val (apply min (neighbors m i j))]
        (when (< val min-val) val)))))

(defn line-to-numbers
  [line]
  (vec
    (map #(read-string (str %))
         line)))

(defn solve-1
  [str-lines]
  (let [m (->> str-lines
               (remove str/blank?)
               (map line-to-numbers)
               vec)
        low (low-points m)]
    (+ (count low)
       (apply + low))))
