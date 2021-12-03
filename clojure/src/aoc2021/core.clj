(ns aoc2021.core)

(defn -main
  "Run the program."
  [& args]
  (let [greeted (or (first args) "World")]
      (println (str "Hello, " greeted "!"))))
