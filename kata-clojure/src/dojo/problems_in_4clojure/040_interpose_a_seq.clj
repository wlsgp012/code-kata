(ns dojo.problems-in-4clojure.040-interpose-a-seq)
;; https://4clojure.oxal.org/#/problem/40

(defn answer
  [z [x & xs]]
  (cons x
        (when (seq xs)
          (lazy-seq (cons z (answer z xs))))))

(= (answer 0 [1 2 3]) [1 0 2 0 3])
(= (apply str (answer ", " ["one" "two" "three"])) "one, two, three")
(= (answer :z [:a :b :c :d]) [:a :z :b :z :c :z :d])

;; others
(fn [x ls]
  (rest (interleave (repeat x) ls)))

(fn [x l] (butlast (interleave l (repeat x))))
