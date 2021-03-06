(ns app.mutations
  (:require [untangled.client.mutations :as m]))

(defn change-size
  "Change the size of the canvas by some (pos or neg) amount.."
  [amount state]
  (let [current-size (get-in @state [:child/by-id 0 :size])
        new-size (+ amount current-size)]
    (swap! state assoc-in [:child/by-id 0 :size] new-size)))

; Make the canvas smaller. This will cause
(defmethod m/mutate 'canvas/make-smaller [{:keys [state]} k p] {:action (partial change-size -20 state)})

(defmethod m/mutate 'canvas/make-bigger [{:keys [state]} k p] {:action (partial change-size 20 state)})

(defmethod m/mutate 'canvas/place-marker [{:keys [state]} k {:keys [coords]}]
  {:action (fn [] (swap! state assoc-in [:child/by-id 0 :marker] coords))})

