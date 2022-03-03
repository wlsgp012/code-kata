(ns dojo.problems-in-4clojure.074-filter-perfect-squres)
;; https://4clojure.oxal.org/#/problem/74

(defn sol
  [x]
  (letfn [(sq [y] (Math/sqrt (Integer/parseInt y)))]
    (clojure.string/join "," (filter #(zero? (- (sq %) (int (sq %)))) (clojure.string/split x #",")))))

(= (sol "4,5,6,7,8,9") "4,9")
(= (sol "15,16,25,36,37") "16,25,36")

;; others

(fn [s]
  (->> (re-seq #"\d+" s)
       (map #(Integer/parseInt %))
       (filter (fn [x]
                 (let [r (int (Math/sqrt x))]
                   (= x (* r r)))))
       (interpose ",")
       (apply str)))

(fn [s]
  (->>
   (.split s ",")
   (map #(Integer/valueOf %))
   (filter #(let [r (Math/sqrt %)] (= (Math/floor r) (Math/ceil r))))
   (interpose ",")
   (apply str)))
