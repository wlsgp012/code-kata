(ns dojo.fp-stroll.lazy
  (:gen-class))

(def tenList '(1 2 3 4 5 6 7 8 9 10))

(defn slowly [x] (Thread/sleep 3600) (+ x 1))

                                        ;(dorun [(println "start") (take 1 (map slowly tenList)) (println "end")])

(defn -main [& args]
  (println "s")
  (take 10 (map slowly tenList))
  (println "e"))

(defn infinite-num [n]
  (cons n
        (lazy-seq (infinite-num (+ n 1)))))


(def word-list '("Jack" "Bauer" "Tony" "Almeida"))

(defn random-from-list [l]
  (nth l
       (rand-int (count l))))

(defn infinite-word [word-list]
  (cons
   (random-from-list word-list)
   (lazy-seq
    (infinite-word word-list))))

(defn makeBig []
  (with-open [w (clojure.java.io/writer "./Bigfile.txt")]
    (doseq [word (take 20000000 (infinite-word word-list))]
           (.write w (str word "\n")))))

(defn doitslow [x]
  (Thread/sleep 1000)
  (println x)
  (+ 1 x))

(def lazylist
  (for [elem '(1 2 3)]
    (doitslow elem)))

(def notlazylist
  (doseq [elem '(1 2 3)]
    (doitslow elem)))

(defn iamgrut []
  (Thread/sleep 3000)
  (println "Hi")
  "I'm grut")

(defn i-will-promise-you [] (promise))

(def his-promise (i-will-promise-you))


(defn set-interval [callback ms ntimes]
  (future
    (dotimes [i ntimes]
      (Thread/sleep ms)
      (callback))))
