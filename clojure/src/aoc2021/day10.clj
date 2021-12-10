(ns aoc2021.day10)

(def matching
  {\) \(
   \} \{
   \> \<
   \] \[})

(def opening #{\( \{ \< \[})

(defn process-char
  [stack ch]
  (cond
    (= (matching ch) (peek stack))
    [(pop stack) nil]
    (opening ch)
    [(conj stack ch) nil]
    :else
    [stack ch]))
