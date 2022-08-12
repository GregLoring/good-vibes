(ns good-vibes.core
  (:gen-class))

(defn msg
  ([name] (str "Hello" (if name (str ", " name) "") "!"))
  ([] (msg nil)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (first args)))
