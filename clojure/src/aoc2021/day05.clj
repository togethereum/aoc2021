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
  [[x1 y1 x2 y2] diagonal]
  (cond
    (= x1 x2)
    (for [i (range (min y1 y2) (inc (max y1 y2)))]
      [x1 i])
    (= y1 y2)
    (for [i (range (min x1 x2) (inc (max x1 x2)))]
      [i y1])
    (and diagonal (= (- x1 x2) (- y1 y2)))
    (let [min-x (min x1 x2)
          min-y (min y1 y2)]
      (for [i (range (inc (- (max x1 x2) min-x)))]
        [(+ min-x i) (+ min-y i)]))
    (and diagonal (= (- x1 x2) (- y2 y1)))
    (let [min-x (min x1 x2)
          max-y (max y1 y2)]
      (for [i (range (inc (- (max x1 x2) min-x)))]
        [(+ min-x i) (- max-y i)]))
    :else
    nil))

(defn draw-line
  [board line diagonal]
  (let [points (points-of-line line diagonal)]
    (reduce (fn [board point]
              (update-in board point inc))
      board
      points)))

(defn board-drawn-with-lines
  [diagonal str-lines]
  (let [lines (map parse-line (remove str/blank? str-lines))
        [max-x max-y] (max-coords lines)
        board (init-matrix (inc max-x) (inc max-y))]
    (reduce (fn [board line]
              (draw-line board line diagonal))
            board
            lines)))

(defn solve
  [diagonal str-lines]
  (->> str-lines
       (board-drawn-with-lines diagonal)
       flatten
       (filter #(< 1 %))
       count))

(defn solve-1
  [str-lines]
  (solve false str-lines))

(defn solve-2
  [str-lines]
  (solve true str-lines))
