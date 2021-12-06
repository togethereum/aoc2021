(ns aoc2021.core)

(defn resolve-name
  [ns-name symbol-name]
  (let [ns-sym (symbol ns-name)]
    (require ns-sym)
    (let [ns (find-ns ns-sym)]
      (ns-resolve ns (symbol symbol-name)))))

(defn solve-day
  "Solve the given day dynamically loading its namespace."
  [day]
  (let [solve (resolve-name (str "aoc2021.day" day) "solve")
        input (str "prod/day" day ".txt")]
    (solve input)))

(defn -main
  "Run the program."
  [& args]
  (let [day (first args)]
    (println (solve-day day))))


