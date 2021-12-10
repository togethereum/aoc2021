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
    (and (empty? stack) (opening ch))
    [[ch] nil]
    (empty? stack)
    [[] ch]
    (= (matching ch) (peek stack))
    [(pop stack) nil]
    (opening ch)
    [(conj stack ch) nil]
    :else
    [stack ch]))

(defn process-line
  [line]
  (reduce
    (fn [[stack err] ch]
      (if err
        [stack err]
        (process-char stack ch)))
    [[] nil]
    (seq line)))

(def score {\) 3, \] 57, \} 1197, \> 25137})

(defn solve-1-lines
  [str-lines]
  (let [bad-chars (map #(-> % process-line second score)
                       str-lines)]
    (apply + (remove nil? bad-chars))))