(ns aoc2021.day05
    (:require [clojure.string :as str]))

(defn parse-line
  [line]
  (map read-string (str/split line #",| -> ")))

(defn max-coords
  [coords]
  (let [[x1 y1 x2 y2] (apply map max coords)]
      [(max x1 x2) (max y1 y2)]))

(defn init-matrix
  [x y]
  (vec
    (repeat x
          (vec (repeat y 0)))))

(defn points-of-line
  [[x1 y1 x2 y2]]
  (cond
   (= x1 x2)
   (for [i (range (min y1 y2) (inc (max y1 y2)))]
     [x1 i])
   (= y1 y2)
   (for [i (range (min x1 x2) (inc (max x1 x2)))]
     [i y1])
   :else
   nil))

(defn draw-line
  [board line]
  (let [points (points-of-line line)]
    (reduce (fn [board point]
              (update-in board point inc))
            board
            points)))

(defn board-drawn-with-lines
  [str-lines]
  (let [lines (map parse-line str-lines)
        [max-x max-y] (max-coords lines)
        board (init-matrix max-x max-y)]
    (reduce (fn [board line]
              (draw-line board line))
            board
            lines)))

(defn solve-1
  [str-lines]
  (->> str-lines
       board-drawn-with-lines
       flatten
       (filter #(> % 1))
       count))

(defn solve-2
  [lines] 
  (first lines))