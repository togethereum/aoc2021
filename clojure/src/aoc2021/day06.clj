(ns aoc2021.day06)

(defn next-day
  [timers]
  (let [new-timers (repeat
                     (count (filter zero? timers))
                     8)
        reset-timers (map #(if (zero? %) 7 %) timers)
        old-timers (vec (map dec reset-timers))]
    (into old-timers new-timers)))
