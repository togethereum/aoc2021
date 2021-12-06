(ns aoc2021.core
  (:require [clojure.java.io :as io]))

(defn resolve-name
  [ns-name symbol-name]
  (let [ns-sym (symbol ns-name)]
    (require ns-sym)
    (let [ns (find-ns ns-sym)]
      (ns-resolve ns (symbol symbol-name)))))

(defn read-lines
  [fname]
  (-> fname io/resource io/file io/reader line-seq))

(defn solve-day
  "Solve the given day dynamically loading its namespace."
  [day puzzle test-or-prod]
  (let [solve (resolve-name (str "aoc2021.day" day) (str "solve-" puzzle))
        input (str test-or-prod "/day" day ".txt")
        lines (read-lines input)]
    (solve lines)))

(defn -main
  "Run the program."
  [& args]
  (let [day (first args)]
    (println (solve-day day 1 "test"))
    (println (solve-day day 1 "prod"))))


