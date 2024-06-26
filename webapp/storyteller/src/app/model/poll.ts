/**
 * The Poll interface represents a poll with its associated data.
 * @interface
 * @property {string} id - The unique identifier of the poll.
 * @property {string} owner - The owner of the poll.
 * @property {string} question - The question of the poll.
 * @property {PollOption[]} options - The options of the poll.
 */
export interface Poll {
    id : string
    owner: string
    question : string
    options : PollOption[]
}

/**
 * The PollOption interface represents an option in a poll with its associated data.
 * @interface
 * @property {string} id - The unique identifier of the option.
 * @property {string} content - The content of the option.
 * @property {number} voteCount - The vote count of the option.
 */
export interface PollOption {
    id : string
    content : string
    voteCount : number
}




