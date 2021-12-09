(ns aoc2021.day09)

(defn neighbor-coords
  [m x y]
  (let [coords [(when (< 0 x) [(dec x) y])
                (when (> (count m) (inc x)) [(inc x) y])
                (when (< 0 y) [x (dec y)])
                (when (> (count (first m)) (inc y)) [x (inc y)])]]
    (remove nil? coords)))

(defn neighbors
  [m x y])

(defn low-points
  [m]
  (let [w (count m)
        h (count (first m))]))
