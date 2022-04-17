(ns dojo.problems-in-4clojure.118-re-implement-map)
;; https://4clojure.oxal.org/#/problem/118

(defn sol [f [x & xs]]
  (if (nil? x)
    nil
   (lazy-seq (cons (f x) (sol f xs)))))

(= [3 4 5 6 7]
   (sol inc [2 3 4 5 6]))

(= (repeat 10 nil)
   (sol (fn [_] nil) (range 10)))

(= [1000000 1000001]
   (->> (sol inc (range))
        (drop (dec 1000000))
        (take 2)))

(= [1000000 1000001]
   (->> (sol inc (range))
        (drop (dec 1000000))
        (take 2)))

;; others
(fn m [f [x & xs]]
  (lazy-seq
   (when x
     (cons (f x) (m f xs)))))
