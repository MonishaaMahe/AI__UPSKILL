import csv

def process_student_scores(file_path):
    """
    Reads a CSV file of student scores and prints summary statistics.
    CSV Format: student_id, name, subject, score
    """
    student_scores = {}
    subject_scores = {}

    try:
        with open(file_path, mode='r') as csv_file:
            reader = csv.DictReader(csv_file)
            for row in reader:
                student_id = row.get('student_id')
                name = row.get('name')
                subject = row.get('subject')
                try:
                    score = float(row.get('score'))
                except (TypeError, ValueError):
                    print(f"Invalid score for student {name}. Skipping row.")
                    continue

                # Aggregate per student
                if student_id not in student_scores:
                    student_scores[student_id] = {'name': name, 'scores': []}
                student_scores[student_id]['scores'].append(score)

                # Aggregate per subject
                if subject not in subject_scores:
                    subject_scores[subject] = []
                subject_scores[subject].append(score)

        # Print student averages
        print("\nStudent Averages:")
        for sid, data in student_scores.items():
            avg = sum(data['scores']) / len(data['scores'])
            print(f"{data['name']} (ID: {sid}) - Average Score: {avg:.2f}")

        # Print top scorer(s)
        top_avg = max(
            sum(s['scores']) / len(s['scores']) for s in student_scores.values()
        )
        print("\nTop Scorer(s):")
        for sid, data in student_scores.items():
            avg = sum(data['scores']) / len(data['scores'])
            if abs(avg - top_avg) < 1e-6:
                print(f"{data['name']} (ID: {sid}) with {avg:.2f}")

        # Print subject-wise averages
        print("\nSubject Averages:")
        for subject, scores in subject_scores.items():
            avg = sum(scores) / len(scores)
            print(f"{subject}: {avg:.2f}")

    except FileNotFoundError:
        print(f"Error: File '{file_path}' not found.")
    except Exception as e:
        print(f"Unexpected error occurred: {e}")
