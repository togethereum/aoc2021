(ns aoc2021.core)

(defn resolve-fn
  [ns-name fn-name]
  (let [ns-sym (symbol ns-name)]
    (require ns-sym)
    (let [ns (find-ns ns-sym)]
      (ns-resolve ns (symbol fn-name)))))

(defn solve-day
  "Solve the given day dynamically loading its namespace."
  [day]
  (let [solve (resolve-fn (str "aoc2021.day" day) "solve")]
    (solve)))

(defn -main
  "Run the program."
  [& args]
  (let [day (first args)]
    (solve-day day)))


