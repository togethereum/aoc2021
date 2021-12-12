(ns aoc2021.day11)

(defn flash?
  [n]
  (= n 9))

(defn neighbor-coords
  [m x y]
  (let [coords [(when (< 0 x) [(dec x) y])
                (when (> (count m) (inc x)) [(inc x) y])
                (when (< 0 y) [x (dec y)])
                (when (> (count (first m)) (inc y)) [x (inc y)])
                (when (and
                        (< 0 x)
                        (< 0 y))
                  [(dec x) (dec y)])
                (when (and
                        (< 0 x)
                        (> (count (first m)) (inc y)))
                  [(dec x) (inc y)])
                (when (and
                        (> (count m) (inc x))
                        (< 0 y))
                  [(inc x) (dec y)])
                (when (and
                        (> (count m) (inc x))
                        (> (count (first m)) (inc y)))
                  [(inc x) (inc y)])]]
    (remove nil? coords)))

(defn flash-coords
  [m]
  (mapcat identity
    (remove nil?
      (for [i (range (count m))
            j (range (count (first m)))]
        (when (flash? (get-in m [i j]))
          (remove #(flash? (get-in m %))
                  (neighbor-coords m i j)))))))


(defn flash
  [m])

(defn one-round
  [m])

