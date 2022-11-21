(ns dojo.problems-in-4clojure.141-tricky-card-games)

;; not exactly
(defn sol2 [trump-suit]
  (fn [cards]
    (letfn [(filter-by-suit [{suit :suit}] (if (nil? trump-suit) true (= suit trump-suit)))
            (sort-by-suit [{suit :suit}] (.indexOf [:spade :heart :diamond :club] suit))]
      (first (sort-by (juxt sort-by-suit (comp - :rank)) (filter filter-by-suit cards))))))

(defn sol [trump-suit]
  (fn [cards]
    (letfn [(filter-by-suit [{suit :suit}] (if (nil? trump-suit) (= suit (:suit (first cards))) (= suit trump-suit)))]
      (first (sort-by (comp - :rank) (filter filter-by-suit cards))))))

(let [notrump (sol nil)]
  (and (= {:suit :club :rank 9}  (notrump [{:suit :club :rank 4}
                                           {:suit :club :rank 9}]))
       (= {:suit :spade :rank 2} (notrump [{:suit :spade :rank 2}
                                           {:suit :club :rank 10}]))))

(= {:suit :club :rank 10} ((sol :club) [{:suit :spade :rank 2}
                                        {:suit :club :rank 10}]))

(= {:suit :heart :rank 8}
   ((sol :heart) [{:suit :heart :rank 6} {:suit :heart :rank 8}
                 {:suit :diamond :rank 10} {:suit :heart :rank 4}]))

;; others
(fn [t]
  #(let [{T t L (-> % first :suit)} (group-by :suit %)]
     (last (sort-by :rank (or T L)))))

(fn winner [trump]
  (fn [cards]
    (let [suit (or trump (:suit (first cards)))]
      (->> cards
           (filter #(= (:suit %) suit))
           (sort-by :rank >)
           first))))

(fn [trump]
  (fn winner [cards]
    (let [high-card (fn [suit]
                      (->> (filter #(= suit (:suit %)) cards)
                           (sort-by :rank)
                           (last)))
          lead (:suit (first cards))]
      (or (high-card trump)
          (high-card lead)))))
