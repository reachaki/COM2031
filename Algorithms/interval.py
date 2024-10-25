class Activity:
    def __init__(self, start, finish):
        self.start = start
        self.finish = finish

def select_activities(activities):
    # Sort activities by their finish time
    activities.sort(key=lambda x: x.finish)

    n = len(activities)
    selected_activities = []

    # The first activity always gets selected
    selected_activities.append(activities[0])
    last_selected = activities[0]  # Keep track of the last selected activity

    for i in range(1, n):
        # If this activity starts after or when the last selected activity finishes
        if activities[i].start >= last_selected.finish:
            selected_activities.append(activities[i])
            last_selected = activities[i]  # Update the last selected activity

    return selected_activities

# Example usage
if __name__ == "__main__":
    activities = [Activity(1, 3), Activity(2, 5), Activity(4, 6), Activity(6, 7), Activity(5, 9), Activity(8, 9)]
    selected = select_activities(activities)

    print("Selected Activities:")
    for activity in selected:
        print(f"Start: {activity.start}, Finish: {activity.finish}")
